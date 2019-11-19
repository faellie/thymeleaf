package com.myropcb.nest;

import com.myropcb.nest.Nest;
import com.myropcb.nest.data.NestPath;
import com.myropcb.nest.data.Placement;
import com.myropcb.nest.util.GeometryUtil;
import com.myropcb.nest.util.NestConfig;
import com.myropcb.nest.util.SvgUtil;
import com.myropcb.pcb.model.rest.PcbEntry;
import com.myropcb.pcb.model.rest.ProductionOrder;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 *
 *
 * zihuangw
 ****************************************************************************
 *
 *
 *
 *
 * Created by zihuangw on 11/13/19.
 *
 ****************************************************************************
 * Copyright (c) 2019 Nokia. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 ****************************************************************************
 */
public class NestNBI {

    //space between each shape when add them up into List<NestPath>
    private static  double X_SPACE_BETWEEN_BIN = 10.0;
    private static  double Y_SPACE_BETWEEN_BIN = 10.0;

    private static  double MAX_X_DIMENSION = 1000.0;

    private static int NEST_INIT_COUNT=100;
    private static int NEST_STEP=20;
    private static int MAX_NEST_LOOP=5;

    public static String runNest(ProductionOrder aInOrder) {
        //test some basic fucntioanlity
        NestPath binPolygon = new NestPath();
        double width = aInOrder.getProductionBoard().getWidth();
        double height = aInOrder.getProductionBoard().getHight();

        binPolygon.add(0, 0);
        binPolygon.add(0, height);
        binPolygon.add(width, height);
        binPolygon.add(width, 0);

        List<NestPath> list = new ArrayList<NestPath>();

        double maxX = X_SPACE_BETWEEN_BIN;
        double maxY = Y_SPACE_BETWEEN_BIN;


        //the max Y in current line
        double currentMaxY = 0.0;
        int bin = 1;
        for(PcbEntry lEntry : aInOrder.getPcbList()) {
            for(int count = 0; count < lEntry.getCount(); count++) {
                NestPath lNextNestPath = new NestPath();
                lNextNestPath.add(maxX, maxY);
                lNextNestPath.add(maxX, maxY + lEntry.getPcb().getHight());
                lNextNestPath.add(maxX + lEntry.getPcb().getWidth(), maxY + lEntry.getPcb().getHight());
                lNextNestPath.add(maxX + lEntry.getPcb().getWidth(), maxY);

                lNextNestPath.setRotation(4);
                lNextNestPath.bid = bin++;
                lNextNestPath.setRotation(4);
                currentMaxY = Math.max(lEntry.getPcb().getHight(), currentMaxY);
                maxX = maxX + X_SPACE_BETWEEN_BIN + lEntry.getPcb().getWidth();
                if (maxX >= MAX_X_DIMENSION) {
                    maxY = currentMaxY + Y_SPACE_BETWEEN_BIN;
                    currentMaxY = 0.0;
                }
                list.add(lNextNestPath);
            }
        }

        NestConfig config = new NestConfig();
        config.USE_HOLE = false;
        config.SPACING = 0.0;
        config.POPULATION_SIZE=10;
        config.CLIIPER_SCALE=10000;
        config.CURVE_TOLERANCE = 0.0;
        Nest nest = new Nest(binPolygon, list, config, NEST_INIT_COUNT);
        List<List<Placement>> appliedPlacement = null;
        appliedPlacement = nest.startNest();
        if(null == appliedPlacement || appliedPlacement.size() == 0 ) {
            System.out.println("Size = " + appliedPlacement.size());
        } else {
            int loop = 0;
            while (appliedPlacement.size() > 1 && loop < MAX_NEST_LOOP) {
                System.out.println("Size = " + appliedPlacement.size());
                nest = new Nest(binPolygon, list, config, NEST_STEP);
                appliedPlacement = nest.startNest();
                loop ++;
            }
        }
        List<String> strings = null;
        String lSvgStr = "";
        try {
            strings = SvgUtil.svgGenerator(list, appliedPlacement, width, height);
            lSvgStr = saveSvgFile(strings);
        } catch (Exception e) {

        }
        for (String s : strings) {
            System.out.println(s);
        }

        //todo calculate usage
        double usedArea  = 0.0;
        for(NestPath path : list) {
            usedArea = GeometryUtil.polygonArea(path) + usedArea;
        }
        double rate = usedArea/GeometryUtil.polygonArea(binPolygon);
        System.out.println("Usage = " + rate);
        return lSvgStr;
    }


    private static String  saveSvgFile(List<String> strings) throws Exception {
        File f = new File("out/test.html");
        String ret = "";
        if (!f.exists()) {
            f.createNewFile();
        }
        Writer writer = new FileWriter(f, false);
        writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                "\n" +
                "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
                "\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
                " \n" +
                "<svg width=\"100%\" height=\"100%\" version=\"1.1\"\n" +
                "xmlns=\"http://www.w3.org/2000/svg\">\n");
        ret  = ret + "<?xml version=\"1.0\" standalone=\"no\"?>\n" +
                "\n" +
                "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
                "\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
                " \n" +
                "<svg width=\"100%\" height=\"100%\" version=\"1.1\"\n" +
                "xmlns=\"http://www.w3.org/2000/svg\">\n";
        for(String s : strings){
            writer.write(s);
            ret  = ret + s;
        }
        writer.write("</svg>");
        writer.close();
        return ret;
    }
}
