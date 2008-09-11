/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.maps.client.overlay;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the Polygon class.
 */
public class PolylineTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before each test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  /**
   * Test the getArea() method.
   */
  public void testGetLength() {
    MapWidget map = new MapWidget(new LatLng(0, 0), 3);
    map.setSize("500px", "400px");
    LatLng[] points = { // 
    new LatLng(45, 45), //
        new LatLng(45, -45), //
        new LatLng(0, 0)};
    Polyline p = new Polyline(points);
    map.addOverlay(p);
    RootPanel.get().add(map);
    double result = p.getLength();
    assertTrue("non-negative length", result > 0);
  }

  /**
   * Test the simple polyline constructor.
   */
  public void testPolyline() {
    MapWidget map = new MapWidget(new LatLng(0, 0), 3);
    map.setSize("500px", "400px");
    LatLng[] points = { // 
    new LatLng(45, 45), //
        new LatLng(45, -45), //
        new LatLng(0, 0)};
    Polyline p = new Polyline(points);
    map.addOverlay(p);
    RootPanel.get().add(map);
  }

  /**
   * Tests the fromEncoded() methods.
   */
  public void testPolylineFromEncoded() {
    MapWidget map = new MapWidget(new LatLng(33.75951619957536,
        -84.39289301633835), 20);
    map.setSize("500px", "400px");
    map.addMapType(MapType.getHybridMap());
    map.setCurrentMapType(MapType.getHybridMap());

    EncodedPolyline[] polylines = new EncodedPolyline[2];
    polylines[0] = EncodedPolyline.newInstance("{t`mEt_bbO?eAx@??dAy@?", 2,
        "BBBBB", 1);
    polylines[0].setColor("#ff00ff");
    polylines[0].setWeight(2);
    polylines[0].setOpacity(0.7);

    polylines[1] = EncodedPolyline.newInstance("au`mEz_bbO?sAbA@?pAcA?", 2,
        "BBBBB", 1, "#0000ff", 2, 0.9);

    Polyline p0 = Polyline.fromEncoded(polylines[0]);
    int vertexCount = p0.getVertexCount();
    assertEquals("vertex count", vertexCount, 5);
    assertTrue("length > 0", p0.getLength() > 0);

    Polyline p1 = Polyline.fromEncoded(polylines[1]);
    vertexCount = p1.getVertexCount();
    assertEquals("vertex count", vertexCount, 5);
    assertTrue("length > 0", p1.getLength() > 0);

    RootPanel.get().add(map);
  }

  /**
   * Test insertVertex() and deleteVerex().
   */
  public void testPolylineInsertDelete() {
    MapWidget map = new MapWidget(new LatLng(0, 0), 3);
    map.setSize("500px", "400px");
    LatLng[] points = { // 
    new LatLng(45, 45), //
        new LatLng(45, -45), //
        new LatLng(0, 0)};
    Polyline p = new Polyline(points);
    map.addOverlay(p);
    RootPanel.get().add(map);
    p.insertVertex(1, new LatLng(45, 0));
    p.deleteVertex(3);
  }

  /**
   * Test the polyline constructor with extra args, but not polylineOptions.
   */
  public void testPolylineNoOpts() {
    MapWidget map = new MapWidget(new LatLng(0, 0), 3);
    map.setSize("500px", "400px");
    LatLng[] points = { // 
    new LatLng(45, 45), //
        new LatLng(45, -45), //
        new LatLng(0, 0)};
    Polyline p = new Polyline(points, "#ff0000", 3, 1.0);
    map.addOverlay(p);
    RootPanel.get().add(map);
  }

  /**
   * Test the constructor using the PolylineOptions argument.
   */
  public void testPolylineOptions() {
    MapWidget map = new MapWidget(new LatLng(0, 0), 3);
    map.setSize("500px", "400px");
    LatLng nycToZurich[] = {new LatLng(40.75, -73.90), // New York
        new LatLng(47.3, 8.5) // Zurich
    };
    map.setCenter(new LatLng(40, -25), 2);
    Polyline pline = new Polyline(nycToZurich, "#FF0000", 1, .75,
        PolylineOptions.newInstance(false, true));
    map.addOverlay(pline);
    RootPanel.get().add(map);
  }

  /**
   * Test the getVertexCount() method.
   */
  public void testPolylineVertexCount() {
    MapWidget map = new MapWidget(new LatLng(0, 0), 3);
    map.setSize("500px", "400px");
    LatLng[] points = { // 
    new LatLng(45, 45), //
        new LatLng(45, -45), //
        new LatLng(0, 0)};
    Polyline p = new Polyline(points);
    map.addOverlay(p);
    RootPanel.get().add(map);
    assertEquals("vertex count", 3, p.getVertexCount());
  }

}
