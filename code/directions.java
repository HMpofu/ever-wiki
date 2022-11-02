private void parseJSon(String data) throws JSONException {
    if (data == null)
        return;

    List<Route> routes = new ArrayList<Route>();
    JSONObject jsonData = new JSONObject(data);
    JSONArray jsonRoutes = jsonData.getJSONArray("routes");
    for (int i = 0; i < jsonRoutes.length(); i++) {
        JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
        Route route = new Route();

        JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
        JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
        JSONObject jsonLeg = jsonLegs.getJSONObject(0);
        JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
        JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
        JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");
        JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");

        route.distance = new Distance(jsonDistance.getString("text"), jsonDistance.getInt("value"));
        route.duration = new Duration(jsonDuration.getString("text"), jsonDuration.getInt("value"));
        route.endAddress = jsonLeg.getString("end_address");
        route.startAddress = jsonLeg.getString("start_address");
        route.startLocation = new LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng"));
        route.endLocation = new LatLng(jsonEndLocation.getDouble("lat"), jsonEndLocation.getDouble("lng"));
        route.points = decodePolyLine(overview_polylineJson.getString("points"));

        routes.add(route);
    }

    listener.onDirectionFinderSuccess(routes);
}