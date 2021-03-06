/*
 * Copyright (c) 2017 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.charts;

import eu.hansolo.fx.charts.data.PlotItem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CircularPlotTest extends Application {
    private CircularPlot circluarPlot;

    @Override public void init() {
        // Setup Data
        // Wahlberechtigte 61_500_000
        PlotItem australia = new PlotItem("AUSTRALIA", 1_250_000, Color.rgb(255, 51, 51));
        PlotItem india     = new PlotItem("INDIA", 750_000, Color.rgb(255, 153, 51));
        PlotItem china     = new PlotItem("CHINA", 920_000, Color.rgb(255, 255, 51));
        PlotItem japan     = new PlotItem("JAPAN", 1_060_000, Color.rgb(153, 255, 51));
        PlotItem thailand  = new PlotItem("THAILAND", 720_000, Color.rgb(51, 255, 51));
        PlotItem singapore = new PlotItem("SINGAPORE", 800_000, Color.rgb(51, 255, 153));

        // Travel flow
        australia.addToOutgoing(india, 150_000);
        australia.addToOutgoing(china, 90_000);
        australia.addToOutgoing(japan, 180_000);
        australia.addToOutgoing(thailand, 15_000);
        australia.addToOutgoing(singapore, 10_000);

        india.addToOutgoing(australia, 35_000);
        india.addToOutgoing(china, 10_000);
        india.addToOutgoing(japan, 40_000);
        india.addToOutgoing(thailand, 25_000);
        india.addToOutgoing(singapore, 8_000);

        china.addToOutgoing(australia, 10_000);
        china.addToOutgoing(india, 7_000);
        china.addToOutgoing(japan, 40_000);
        china.addToOutgoing(thailand, 5_000);
        china.addToOutgoing(singapore, 4_000);

        japan.addToOutgoing(australia, 7_000);
        japan.addToOutgoing(india, 8_000);
        japan.addToOutgoing(china, 175_000);
        japan.addToOutgoing(thailand, 11_000);
        japan.addToOutgoing(singapore, 18_000);

        thailand.addToOutgoing(australia, 70_000);
        thailand.addToOutgoing(india, 30_000);
        thailand.addToOutgoing(china, 22_000);
        thailand.addToOutgoing(japan, 120_000);
        thailand.addToOutgoing(singapore, 40_000);

        singapore.addToOutgoing(australia, 60_000);
        singapore.addToOutgoing(india, 90_000);
        singapore.addToOutgoing(china, 110_000);
        singapore.addToOutgoing(japan, 14_000);
        singapore.addToOutgoing(thailand, 30_000);


        // Setup Chart
        circluarPlot = CircularPlotBuilder.create()
                                          .prefSize(500, 500)
                                          .items(australia, india, china, japan, thailand, singapore)
                                          .connectionOpacity(0.75)
                                          .decimals(0)
                                          .minorTickMarksVisible(false)
                                          .build();
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane(circluarPlot);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("Circular Plot");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
