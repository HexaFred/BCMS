package com.MichelixBorri.BCMS;
// https://eclipse-ee4j.github.io/tyrus-project.github.io/documentation/latest/index/

public class Serveur {

    public static boolean CriseEnCours = false;
    public static boolean PompierConnecte = false;
    public static boolean PolicierConnecte = false;
    public static boolean VehiclesPolicier = false;
    public static boolean VehiclesPompier = false;
    public static boolean RoutePV = false;
    public static boolean RouteFT = false;
    public static boolean AgreeRoutePV = false;
    public static boolean AgreeRouteFT = false;
    public static boolean DisagreeRoutePV = false;
    public static boolean DisagreeRouteFT = false;
    public static boolean NoMoreRouteLeft = false;
    public static boolean DispatchPV = false;
    public static boolean DispatchFT = false;
    public static boolean BreakdownPV = false;
    public static boolean BreakdownFT = false;
    public static boolean ArrivedPV = false;
    public static boolean ArrivedFT = false;
    public static boolean BlockedPV = false;
    public static boolean BlockedFT = false;
    public static boolean CrisisMoreSevere = false;
    public static boolean CrisisLessSevere = false;
    public static int nbVehiclesPompier;
    public static int nbVehiclesPolicier;
    static BCMS bCMS;

    /* Danger: 'My_ServerEndpoint' constructor must be accessed by the WebSockets server. Don't forget 'static'! */
    @javax.websocket.server.ServerEndpoint(value = "/BCMS")
    public static class My_ServerEndpoint { // It *MUST* be 'public'!

        @javax.websocket.OnClose
        public void onClose(javax.websocket.Session session, javax.websocket.CloseReason close_reason) {
            System.out.println("onClose: " + close_reason.getReasonPhrase());
        }

        @javax.websocket.OnError
        public void onError(javax.websocket.Session session, Throwable throwable) {
            System.out.println("onError: " + throwable.getMessage());
        }

        @javax.websocket.OnMessage
        public void onMessage(javax.websocket.Session session, String message) throws java.io.IOException {
            System.out.println("Message from client: " + message);

            if ((message.contains("PolicierVehicles")) && PolicierConnecte) {
                char nb;
                nb = message.charAt(18);
                nbVehiclesPolicier = Character.getNumericValue(nb);
                try {
                    bCMS.state_police_vehicle_number(nbVehiclesPolicier);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                VehiclesPolicier = true;
            } else if ((message.contains("PompierVehicles")) && PompierConnecte) {
                char nb;
                nb = message.charAt(17);
                nbVehiclesPompier = Character.getNumericValue(nb);
                try {
                    bCMS.state_fire_truck_number(nbVehiclesPompier);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                VehiclesPompier = true;
            } else if (message.contains("PoliceVehicleBreakdown")) {
                char nb;
                nb = message.charAt(23);
                try {
                    bCMS.police_vehicle_breakdown("Police vehicle #" + nb, "");
                    for (String s : bCMS.get_police_vehicles(BCMS.Status.Breakdown)) {
                        System.out.println(BCMS.Status.Breakdown + ": " + s);
                    }
                    BreakdownPV = true;
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            } else if (message.contains("PompierVehicleBreakdown")) {
                char nb;
                nb = message.charAt(25);
                try {
                    bCMS.fire_truck_breakdown("Fire truck #" + nb, "");

                    for (String s : bCMS.get_fire_trucks(BCMS.Status.Breakdown)) {
                        System.out.println(BCMS.Status.Breakdown + ": " + s);
                    }
                    BreakdownFT = true;
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            } else if (message.contains("FireTruckArrived")) {
                for (int i = 0; i <= nbVehiclesPompier; i++) {
                    try {
                        bCMS.fire_truck_arrived("Fire truck #" + i);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
                try {
                    for (String s : bCMS.get_fire_trucks(BCMS.Status.Arrived)) {
                        System.out.println(BCMS.Status.Arrived + ": " + s);
                    }
                    ArrivedFT = false;
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            } else if (message.contains("PoliceVehicleArrived")) {
                for (int i = 0; i <= nbVehiclesPompier; i++) {
                    try {
                        bCMS.police_vehicle_arrived("Police vehicle #" + i);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
                try {
                    for (String s : bCMS.get_police_vehicles(BCMS.Status.Arrived)) {
                        System.out.println(BCMS.Status.Arrived + ": " + s);
                    }
                    ArrivedPV = true;

                } catch (Throwable t) {
                    t.printStackTrace();
                }
            } else {
                switch (message) {

                    /* Add a crisis */
                    case ("\"AjouterCrise\""):
                        if (!CriseEnCours) {
                            try {
                                bCMS.start();
                                CriseEnCours = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;

                    /* Connect as a policeman */
                    case ("\"Policier\""):
                        if (CriseEnCours && !PolicierConnecte) {
                            try {
                                bCMS.PSC_connection_request();
                                PolicierConnecte = true;
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                        break;

                    /* Connect as a firefighter */
                    case ("\"Pompier\""):
                        if (CriseEnCours && !PompierConnecte) {
                            try {
                                bCMS.FSC_connection_request();
                                PompierConnecte = true;
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                        break;

                    /* The police choose the route to take for their vehicles  */
                    case ("\"Route1PV\""):
                    case ("\"Route2PV\""):
                    case ("\"Route3PV\""):
                        if (VehiclesPolicier) {
                            try {
                                bCMS.route_for_police_vehicles();
                                RoutePV = true;
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                        break;

                    /* The police choose the route to take for the firetrucks  */
                    case ("\"Route1FT\""):
                    case ("\"Route2FT\""):
                    case ("\"Route3FT\""):
                        if (VehiclesPompier) {
                            try {
                                bCMS.route_for_fire_trucks();
                                RouteFT = true;
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                        break;

                    /* The firefighters refuse the route for the police vehicles  */
                    case ("\"RefuseRoute1PV\""):
                    case ("\"RefuseRoute2PV\""):
                    case ("\"RefuseRoute3PV\""):
                    case ("\"RefuseRoute1_2PV\""):
                    case ("\"RefuseRoute1_3PV\""):
                    case ("\"RefuseRoute2_3PV\""):
                        try {
                        bCMS.FSC_disagrees_about_police_vehicle_route();
                        DisagreeRoutePV = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* The firefighters refuse the route for the firetrucks  */
                    case ("\"RefuseRoute1FT\""):
                    case ("\"RefuseRoute2FT\""):
                    case ("\"RefuseRoute3FT\""):
                    case ("\"RefuseRoute1_2FT\""):
                    case ("\"RefuseRoute1_3FT\""):
                    case ("\"RefuseRoute2_3FT\""):
                        try {
                        bCMS.FSC_disagrees_about_fire_truck_route();
                        DisagreeRouteFT = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* The firefighters agree the route to take for the police vehicles  */
                    case ("\"AgreeRoute1PV\""):
                    case ("\"AgreeRoute2PV\""):
                    case ("\"AgreeRoute3PV\""):
                        try {
                        bCMS.FSC_agrees_about_police_vehicle_route();
                        AgreeRoutePV = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* The firefighters agree the route to take for the firetrucks  */
                    case ("\"AgreeRoute1FT\""):
                    case ("\"AgreeRoute2FT\""):
                    case ("\"AgreeRoute3FT\""):
                        try {
                        bCMS.FSC_agrees_about_fire_truck_route();
                        AgreeRouteFT = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* No more route left to be chosen for the PV or the FT  */
                    case ("\"NoMoreRouteLeftPV\""):
                    case ("\"NoMoreRouteLeftFT\""):
                        try {
                        bCMS.no_more_route_left();
                        NoMoreRouteLeft = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* Number of firetrucks dispatched  */
                    case ("\"AllVehiclesDispatchedFT\""):
                        for (int i = 0; i <= nbVehiclesPompier; i++) {
                            try {
                                bCMS.fire_truck_dispatched("Fire truck #" + i);
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                        try {
                            for (String s : bCMS.get_fire_trucks(BCMS.Status.Dispatched)) {
                                System.out.println(BCMS.Status.Dispatched + ": " + s);
                                DispatchFT = true;
                            }
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                        break;

                    /* Number of police vehicles dispatched  */
                    case ("\"AllVehiclesDispatchedPV\""):
                        for (int i = 0; i <= nbVehiclesPolicier; i++) {
                            try {
                                bCMS.police_vehicle_dispatched("Police vehicle #" + i);
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                        try {
                            for (String s : bCMS.get_police_vehicles(BCMS.Status.Dispatched)) {
                                System.out.println(BCMS.Status.Dispatched + ": " + s);
                                DispatchPV = true;
                            }
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                        break;

                    /* Crisis is more severe  */
                    case ("\"CrisisMoreSevere\""):
                        try {
                        bCMS.crisis_is_more_severe();
                        CrisisMoreSevere = true;

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* Crisis is less severe  */
                    case ("\"CrisisLessSevere\""):
                        try {
                        bCMS.crisis_is_less_severe();
                        CrisisLessSevere = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* Fire trucks blocked  */
                    case ("\"VehiculeBlockedFT\""):
                        try {
                        bCMS.fire_truck_blocked("Fire trucks");
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    try {
                        for (String s : bCMS.get_fire_trucks(BCMS.Status.Blocked)) {
                            System.out.println(BCMS.Status.Blocked + ": " + s);
                        }
                        BlockedFT = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* Police vehicles blocked  */
                    case ("\"VehiculeBlockedPV\""):
                        try {
                        bCMS.police_vehicle_blocked("Police vehicles");
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    try {
                        for (String s : bCMS.get_police_vehicles(BCMS.Status.Blocked)) {
                            System.out.println(BCMS.Status.Blocked + ": " + s);
                        }
                        BlockedPV = true;
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* Close the crisis */
                    case ("\"Close\""):
                        try {
                        bCMS.close();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* Delete the crisis from the DataBase */
                    case ("\"Stop\""):
                        try {
                        bCMS.stop();
                        System.exit(0);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;

                    /* Reset the crisis (close/delete the crisis and open a new page to begin a new crisis */
                    case ("\"Reset\""):
                        try {
                        bCMS.close();
                        bCMS.stop();
                        java.awt.Desktop.getDesktop().browse(java.nio.file.FileSystems.getDefault().getPath("IHM" + java.io.File.separatorChar + "index.html").toUri());
                        /*restart the web page in java, open a new page, reset the websocket...Failed, close the previous page web*/

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    break;
                }
            }
        }

        @javax.websocket.OnOpen
        public void onOpen(javax.websocket.Session session, javax.websocket.EndpointConfig ec) throws java.io.IOException {
            System.out.println("OnOpen... " + ec.getUserProperties().get("Author"));
            session.getBasicRemote().sendText("{\"Handshaking\": \"Yes\"}");
            try {
                CriseEnCours = false;
                PompierConnecte = false;
                PolicierConnecte = false;
                VehiclesPolicier = false;
                VehiclesPompier = false;
                RoutePV = false;
                RouteFT = false;
                AgreeRoutePV = false;
                AgreeRouteFT = false;
                DisagreeRoutePV = false;
                DisagreeRouteFT = false;
                NoMoreRouteLeft = false;
                DispatchPV = false;
                DispatchFT = false;
                BreakdownPV = false;
                BreakdownFT = false;
                ArrivedPV = false;
                ArrivedFT = false;
                BlockedPV = false;
                BlockedFT = false;
                CrisisMoreSevere = false;
                CrisisLessSevere = false;
                bCMS = new BCMS();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {

        java.util.Map<String, Object> user_properties = new java.util.HashMap();
        user_properties.put("Author", "MichelixBorri");

        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 1963, "/MichelixBorri", user_properties /* or 'null' */, My_ServerEndpoint.class);
        try {
            server.start();
            System.out.println("\n*** Please press any key to stop the server... ***\n");
// The Web page (JavaScript client) is launched from Java:
            java.awt.Desktop.getDesktop().browse(java.nio.file.FileSystems.getDefault().getPath("IHM" + java.io.File.separatorChar + "index.html").toUri());
// The Java 11 client is launched as well:
            Java_11_client client = new Java_11_client(java.util.Optional.of(Java_11_client.class.getSimpleName()));
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            reader.readLine();
            System.out.println("\n@@@\n" + client.get_log() + "@@@\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
