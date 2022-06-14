"use strict";
/* - MICHELIX Quentin & BORRI Frédéric - */
window.onload = () => {
    // Tested with Tyrus 1.17 WebSockets Java library
    const service = new WebSocket("ws://localhost:1963/MichelixBorri/BCMS");
    service.onmessage = (event) => {
        console.log("Message from Java: " + event.data);
    };
    service.onopen = () => {
        console.log("service.onopen...");
        const response = window.confirm(service.url + " just opened... Say 'Hi!'?");
        if (response)
            service.send(JSON.stringify({ Response: "Hi!" }));
    };
    service.onclose = (event /*:CloseEvent*/) => {
        console.log("service.onclose... " + event.code);
        window.alert("Bye! See you later...");
        // '1011': the server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.
    };
    service.onerror = () => {
        window.alert("service.onerror...");
    };
    /* -------- popups toggles -------- */
    /* - popup toggle vehicles - */
    function toggle() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('PoliceVehicleConfirm');
        popup.classList.toggle('active');
    }
    function toggle2() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('PompierVehicleConfirm');
        popup.classList.toggle('active');
    }
    /* - popup toggle routes PV - */
    function toggle3() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('RoutePoliceConfirm');
        popup.classList.toggle('active');
    }
    function toggle4() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('RoutePompierConfirm');
        popup.classList.toggle('active');
    }
    /* - popup toggle routes FT - */
    function toggle5() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('RoutePoliceConfirm2');
        popup.classList.toggle('active');
    }
    function toggle6() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('RoutePompierConfirm2');
        popup.classList.toggle('active');
    }
    /* - popup toggle dispatch - */
    function toggle7() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('DispatchPV');
        popup.classList.toggle('active');
    }
    function toggle8() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('DispatchFT');
        popup.classList.toggle('active');
    }
    /* - popup toggle breakdown - */
    function toggle9() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('breakdownPV');
        popup.classList.toggle('active');
    }
    function toggle10() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('breakdownFT');
        popup.classList.toggle('active');
    }
    /* - popup toggle blocked - */
    function toggle13() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('blockedVEHICLES');
        popup.classList.toggle('active');
    }
    /* - popup toggle arrived - */
    function toggle11() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('arrivedPV');
        popup.classList.toggle('active');
    }
    function toggle12() {
        var blur = document.getElementById('blur');
        blur.classList.toggle('active');
        var popup = document.getElementById('arrivedFT');
        popup.classList.toggle('active');
    }
    /* -------- variables -------- */
    /* - crisis button - */
    let Crise = document.getElementById("Crise");
    /* - connections buttons - */
    let connexionPolicier = document.getElementById("policier");
    let connexionPompier = document.getElementById("pompier");
    /* - connections checkboxs - */
    let checkPolice = document.getElementById("checkPolice");
    let checkPompier = document.getElementById("checkPompier");
    let SeecheckPolice = document.getElementById("SeecheckPolice");
    let SeecheckPompier = document.getElementById("SeecheckPompier");
    /* - popup confirm buttons - */
    let ConfirmPolice = document.getElementById("ConfirmPolice");
    let ConfirmPompier = document.getElementById("ConfirmPompier");
    let ConfirmRoutePolice = document.getElementById("ConfirmRoutePolice");
    let ConfirmRoutePompier = document.getElementById("ConfirmRoutePompier");
    let ConfirmRoutePolice2 = document.getElementById("ConfirmRoutePolice2");
    let ConfirmRoutePompier2 = document.getElementById("ConfirmRoutePompier2");
    let ConfirmDispatchPV = document.getElementById("ConfirmDispatchPV");
    let ConfirmDispatchFT = document.getElementById("ConfirmDispatchFT");
    let ConfirmBreakdownPV = document.getElementById("ConfirmBreakdownPV");
    let ConfirmBreakdownFT = document.getElementById("ConfirmBreakdownFT");
    let ConfirmBreakdownPV_ = document.getElementById("ConfirmBreakdownPV_");
    let ConfirmBreakdownFT_ = document.getElementById("ConfirmBreakdownFT_");
    let ConfirmArrivedPV = document.getElementById("ConfirmArrivedPV");
    let ConfirmArrivedFT = document.getElementById("ConfirmArrivedFT");
    /* - popup cancel buttons - */
    let Cancel = document.getElementById("Cancel");
    let Cancel2 = document.getElementById("Cancel2");
    let Cancel3 = document.getElementById("Cancel3");
    let Cancel4 = document.getElementById("Cancel4");
    let Cancel5 = document.getElementById("Cancel5");
    let Cancel6 = document.getElementById("Cancel6");
    let Cancel7 = document.getElementById("Cancel7");
    let Cancel8 = document.getElementById("Cancel8");
    /* - steps - */
    let Etape1 = document.getElementById("etape1");
    let Etape2 = document.getElementById("etape2");
    let Etape3 = document.getElementById("etape3");
    let Etape3_2 = document.getElementById("etape3_2");
    let Etape4 = document.getElementById("etape4");
    let Etape5 = document.getElementById("etape5");
    let etape5_3 = document.getElementById("etape5_3");
    let Etape6 = document.getElementById("etape6");
    /* - images - */
    let Icon3 = document.getElementById("icon3");
    let Icon4 = document.getElementById("icon4");
    let Icon5 = document.getElementById("icon5");
    let Icon5_2 = document.getElementById("icon5_2");
    let Icon6 = document.getElementById("icon6");
    let Icon6_2 = document.getElementById("icon6_2");
    let Icon7 = document.getElementById("icon7");
    let Icon8 = document.getElementById("icon8");
    let Icon9 = document.getElementById("icon9");
    let Icon10 = document.getElementById("icon10");
    let Icon11 = document.getElementById("icon11");
    let Icon12 = document.getElementById("icon12");
    let Icon13 = document.getElementById("icon13");
    let Icon13_2 = document.getElementById("icon13_2");
    let Icon13_3 = document.getElementById("icon13_3");
    let Icon13_4 = document.getElementById("icon13_4");
    /* - Instructions a tester - */
    let Instructions1 = document.getElementById("Instructions1");
    let Instructions1_1 = document.getElementById("Instructions1_1");
    let Instructions2 = document.getElementById("Instructions2");
    let Instructions2_2 = document.getElementById("Instructions2_2");
    let Instructions3 = document.getElementById("Instructions3");
    let Instructions3_2 = document.getElementById("Instructions3_2");
    let Instructions4 = document.getElementById("Instructions4");
    let Instructions4_2 = document.getElementById("Instructions4_2");
    let Instructions5 = document.getElementById("Instructions5");
    let Instructions5_2 = document.getElementById("Instructions5_2");
    let Instructions5_3 = document.getElementById("Instructions5_3");
    let Instructions5_4 = document.getElementById("Instructions5_4");
    /* - vehicles buttons - */
    let PompierVehicles = document.getElementById("PompierVehicles");
    let PolicierVehicles = document.getElementById("PolicierVehicles");
    let nbVehiclesPolicier = document.getElementById("nbVehiclesPolicier");
    let nbVehiclesPompier = document.getElementById("nbVehiclesPompier");
    /* - routes buttons - */
    let RoutePolice = document.getElementById("RoutePolicier");
    let RoutePompier = document.getElementById("RoutePompier");
    let RoutePolice2 = document.getElementById("RoutePolicier2");
    let RoutePompier2 = document.getElementById("RoutePompier2");
    let R1Police = document.getElementById("R1Police");
    let R2Police = document.getElementById("R2Police");
    let R3Police = document.getElementById("R3Police");
    let R1PoliceCheck = document.getElementById("R1PompierCheck");
    let R2PoliceCheck = document.getElementById("R2PompierCheck");
    let R3PoliceCheck = document.getElementById("R3PompierCheck");
    let boolR1Policier = false;
    let boolR2Policier = false;
    let boolR3Policier = false;
    let R1Pompier = document.getElementById("R1Pompier");
    let R2Pompier = document.getElementById("R2Pompier");
    let R3Pompier = document.getElementById("R3Pompier");
    let R1PompierCheck = document.getElementById("R1PompierCheck");
    let R2PompierCheck = document.getElementById("R2PompierCheck");
    let R3PompierCheck = document.getElementById("R3PompierCheck");
    let boolR1Pompier = false;
    let boolR2Pompier = false;
    let boolR3Pompier = false;
    /* - dispatch buttons - */
    let PVsent = document.getElementById("PVsent");
    let FTsent = document.getElementById("FTsent");
    /* - breakdown buttons - */
    let PVbreakdown = document.getElementById("PVbreakdown");
    let FTbreakdown = document.getElementById("FTbreakdown");
    let CancelSelectionPV = document.getElementById("CancelSelectionPV");
    let CancelSelectionFT = document.getElementById("CancelSelectionFT");
    let PV1Police = document.getElementById("PV1Police");
    let PV2Police = document.getElementById("PV2Police");
    let PV3Police = document.getElementById("PV3Police");
    let FT1Pompier = document.getElementById("FT1Pompier");
    let FT2Pompier = document.getElementById("FT2Pompier");
    let FT3Pompier = document.getElementById("FT3Pompier");
    let FT4Pompier = document.getElementById("FT4Pompier");
    /* - crisis state buttons - */
    let CrisisMoreSevere = document.getElementById("CrisisMoreSevere");
    let blocked_vehicle = document.getElementById("blocked_vehicle");
    let CrisisLessSevere = document.getElementById("CrisisLessSevere");
    let RAS = document.getElementById("RAS");
    /* - blocked vehicles buttons - */
    let PVPoliceBlocked = document.getElementById("PVPoliceblocked");
    let FTPompierBlocked = document.getElementById("FTPompierblocked");
    let ConfirmBlockedPV = document.getElementById("ConfirmBlockedPV");
    let ConfirmBlockedFT = document.getElementById("ConfirmBlockedFT");
    /* - arrived buttons - */
    let PVArrived = document.getElementById("PVArrived");
    let FTArrived = document.getElementById("FTArrived");
    /* - Close buttons - */
    let CloseButton = document.getElementById("CloseButton");
    let ResetButton = document.getElementById("ResetButton");
    /* -------- receive data from web page -------- */
    /* - step 0 - */
    /* - crisis button - */
    Crise.onclick = () => {
        service.send(JSON.stringify("AjouterCrise"));
        Crise.disabled = true;
        Crise.style.top = '0px';
        Crise.style.left = '0px';
        Crise.style.boxShadow = 'none';
        Crise.style.background = 'lightgray';
        Crise.style.color = "black";
        Crise.style.cursor = 'not-allowed';
        connexionPolicier.disabled = false;
        connexionPolicier.style.visibility = 'visible';
        SeecheckPolice.style.visibility = 'visible';
        connexionPompier.disabled = false;
        connexionPompier.style.visibility = 'visible';
        SeecheckPompier.style.visibility = 'visible';
        Etape1.style.visibility = 'visible';
        console.log("Crisis started !");
    };
    /* - step 1 - */
    /* - connections buttons - */
    connexionPolicier.onclick = () => {
        service.send(JSON.stringify("Policier"));
        connexionPolicier.style.cursor = 'not-allowed';
        connexionPolicier.style.backgroundColor = 'gray';
        connexionPolicier.style.top = '0px';
        connexionPolicier.style.left = '0px';
        connexionPolicier.disabled = true;
        checkPolice.checked = true;
        check();
        console.log("Police officer connected !");
    };
    connexionPompier.onclick = () => {
        service.send(JSON.stringify("Pompier"));
        connexionPompier.style.cursor = 'not-allowed';
        connexionPompier.style.backgroundColor = 'gray';
        connexionPompier.style.top = '0px';
        connexionPompier.style.right = '0px';
        connexionPompier.disabled = true;
        checkPompier.checked = true;
        check();
        console.log("Firefighter connected !");
    };
    /* - check connection function to unlock Step 2 */
    function check() {
        if ((connexionPolicier.disabled == true) && (connexionPompier.disabled == true)) {
            Etape2.style.visibility = 'visible';
            Icon3.style.visibility = 'visible';
            Icon4.style.visibility = 'visible';
            PolicierVehicles.style.visibility = 'visible';
            PompierVehicles.style.visibility = 'visible';
            nbVehiclesPolicier.style.visibility = 'visible';
            nbVehiclesPompier.style.visibility = 'visible';
            Instructions1.style.visibility = 'visible';
            Instructions1_1.style.visibility = 'visible';
        }
    }
    /* - step 2 - */
    /* - vehicles buttons - */
    PolicierVehicles.onclick = () => {
        let nbVehiclesPolicier = document.getElementById("nbVehiclesPolicier").value;
        if ((parseInt(nbVehiclesPolicier) < 1) || (parseInt(nbVehiclesPolicier) > 3) || nbVehiclesPolicier == '') {
            console.log("Error, you need to put a value between 1 to 3");
        }
        else {
            toggle();
        }
    };
    /* valeur nb  vehicles*/
    PompierVehicles.onclick = () => {
        let nbVehiclesPompier = document.getElementById("nbVehiclesPompier").value;
        if ((parseInt(nbVehiclesPompier) < 1) || (parseInt(nbVehiclesPompier) > 4) || (nbVehiclesPompier == '')) {
            console.log("Error, you need to put a value between 1 to 4");
        }
        else {
            toggle2();
        }
    };
    /* - confirm vehicles buttons - */
    ConfirmPolice.onclick = () => {
        let nbVehiclesPolicier = document.getElementById("nbVehiclesPolicier").value;
        let nb = "PolicierVehicles " + nbVehiclesPolicier;
        service.send(JSON.stringify(nb));
        PolicierVehicles.disabled = true;
        PolicierVehicles.style.top = '0px';
        PolicierVehicles.style.left = '0px';
        PolicierVehicles.style.cursor = 'not-allowed';
        PolicierVehicles.style.color = 'gray';
        PolicierVehicles.style.color = 'black';
        PolicierVehicles.style.backgroundColor = 'lightgray';
        PolicierVehicles.style.boxShadow = 'none';
        toggle();
        check2();
        console.log("Police Vehicles Confirmed !");
    };
    ConfirmPompier.onclick = () => {
        let nbVehiclesPompier = document.getElementById("nbVehiclesPompier").value;
        let nb = "PompierVehicles " + nbVehiclesPompier;
        service.send(JSON.stringify(nb));
        PompierVehicles.disabled = true;
        PompierVehicles.style.top = '0px';
        PompierVehicles.style.right = '0px';
        PompierVehicles.style.cursor = 'not-allowed';
        PompierVehicles.style.color = 'black';
        PompierVehicles.style.backgroundColor = 'lightgray';
        PompierVehicles.style.boxShadow = 'none';
        toggle2();
        check2();
        console.log("Fire Trucks Confirmed !");
    };
    /* - cancel vehicles buttons - */
    Cancel.onclick = () => {
        toggle();
    };
    Cancel2.onclick = () => {
        toggle2();
    };
    /* - check connection function to unlock Step 3 */
    function check2() {
        if ((PolicierVehicles.disabled == true) && (PompierVehicles.disabled == true)) {
            Etape3.style.visibility = 'visible';
            Etape3_2.style.visibility = 'visible';
            Icon5.style.visibility = 'visible';
            Icon5_2.style.visibility = 'visible';
            Icon6.style.visibility = 'visible';
            Icon6_2.style.visibility = 'visible';
            Instructions2.style.visibility = 'visible';
            Instructions2_2.style.visibility = 'visible';
            Instructions3.style.visibility = 'visible';
            Instructions3_2.style.visibility = 'visible';
            RoutePolice.style.visibility = 'visible';
            RoutePompier.style.visibility = 'visible';
            RoutePolice2.style.visibility = 'visible';
            RoutePompier2.style.visibility = 'visible';
            R1Police.style.visibility = 'visible';
            R2Police.style.visibility = 'visible';
            R3Police.style.visibility = 'visible';
            R1Pompier.style.visibility = 'visible';
            R2Pompier.style.visibility = 'visible';
            R3Pompier.style.visibility = 'visible';
        }
    }
    /* - step 3 */
    /* - routes buttons - */
    /* - PV choices - */
    R1Police.onclick = () => {
        R1Police.disabled = true;
        R1Police.style.cursor = 'not-allowed';
        R1Police.style.color = 'black';
        R1Police.style.backgroundColor = 'lightgray';
        R1Police.style.top = '-10px';
        R1Police.style.scale = '1.15';
        R1PoliceCheck.checked = true;
        RoutePolice.disabled = false;
        RoutePolice.style.cursor = 'pointer';
        if (boolR3Policier == false) {
            R3Police.disabled = false;
            R3Police.style.cursor = 'pointer';
            R3Police.style.color = 'white';
            R3Police.style.backgroundColor = 'black';
            R3Police.style.top = '0px';
            R3Police.style.scale = '1';
            R3PoliceCheck.checked = false;
        }
        if (boolR2Policier == false) {
            R2Police.disabled = false;
            R2Police.style.cursor = 'pointer';
            R2Police.style.color = 'white';
            R2Police.style.backgroundColor = 'black';
            R2Police.style.top = '0px';
            R2Police.style.scale = '1';
            R2PoliceCheck.checked = false;
        }
    };
    R2Police.onclick = () => {
        R2Police.disabled = true;
        R2Police.style.cursor = 'not-allowed';
        R2Police.style.color = 'black';
        R2Police.style.backgroundColor = 'lightgray';
        R2Police.style.top = '-10px';
        R2Police.style.scale = '1.15';
        R2PoliceCheck.checked = true;
        RoutePolice.disabled = false;
        RoutePolice.style.cursor = 'pointer';
        if (boolR3Policier == false) {
            R3Police.disabled = false;
            R3Police.style.cursor = 'pointer';
            R3Police.style.color = 'white';
            R3Police.style.backgroundColor = 'black';
            R3Police.style.top = '0px';
            R3Police.style.scale = '1';
            R3PoliceCheck.checked = false;
        }
        if (boolR1Policier == false) {
            R1Police.disabled = false;
            R1Police.style.cursor = 'pointer';
            R1Police.style.color = 'white';
            R1Police.style.backgroundColor = 'black';
            R1Police.style.top = '0px';
            R1Police.style.scale = '1';
            R1PoliceCheck.checked = false;
        }
    };
    R3Police.onclick = () => {
        R3Police.disabled = true;
        R3Police.style.cursor = 'not-allowed';
        R3Police.style.color = 'black';
        R3Police.style.backgroundColor = 'lightgray';
        R3Police.style.top = '-10px';
        R3Police.style.scale = '1.15';
        R3PoliceCheck.checked = true;
        RoutePolice.disabled = false;
        RoutePolice.style.cursor = 'pointer';
        if (boolR2Policier == false) {
            R2Police.disabled = false;
            R2Police.style.cursor = 'pointer';
            R2Police.style.color = 'white';
            R2Police.style.backgroundColor = 'black';
            R2Police.style.top = '0px';
            R2Police.style.scale = '1';
            R2PoliceCheck.checked = false;
        }
        if (boolR1Policier == false) {
            R1Police.disabled = false;
            R1Police.style.cursor = 'pointer';
            R1Police.style.color = 'white';
            R1Police.style.backgroundColor = 'black';
            R1Police.style.top = '0px';
            R1Police.style.scale = '1';
            R1PoliceCheck.checked = false;
        }
    };
    /* - FT choices - */
    R1Pompier.onclick = () => {
        R1Pompier.disabled = true;
        R1Pompier.style.cursor = 'not-allowed';
        R1Pompier.style.color = 'black';
        R1Pompier.style.backgroundColor = 'lightgray';
        R1Pompier.style.top = '-10px';
        R1Pompier.style.scale = '1.15';
        R1PompierCheck.checked = true;
        RoutePolice2.disabled = false;
        RoutePolice2.style.cursor = 'pointer';
        if (boolR2Pompier == false) {
            R2Pompier.disabled = false;
            R2Pompier.style.cursor = 'pointer';
            R2Pompier.style.color = 'white';
            R2Pompier.style.backgroundColor = 'black';
            R2Pompier.style.top = '0px';
            R2Pompier.style.scale = '1';
            R2PompierCheck.checked = false;
        }
        if (boolR3Pompier == false) {
            R3Pompier.disabled = false;
            R3Pompier.style.cursor = 'pointer';
            R3Pompier.style.color = 'white';
            R3Pompier.style.backgroundColor = 'black';
            R3Pompier.style.top = '0px';
            R3Pompier.style.scale = '1';
            R3PompierCheck.checked = false;
        }
    };
    R2Pompier.onclick = () => {
        R2Pompier.disabled = true;
        R2Pompier.style.cursor = 'not-allowed';
        R2Pompier.style.color = 'black';
        R2Pompier.style.backgroundColor = 'lightgray';
        R2Pompier.style.top = '-10px';
        R2Pompier.style.scale = '1.15';
        R2PompierCheck.checked = true;
        RoutePolice2.disabled = false;
        RoutePolice2.style.cursor = 'pointer';
        if (boolR1Pompier == false) {
            R1Pompier.disabled = false;
            R1Pompier.style.cursor = 'pointer';
            R1Pompier.style.color = 'white';
            R1Pompier.style.backgroundColor = 'black';
            R1Pompier.style.top = '0px';
            R1Pompier.style.scale = '1';
            R1PompierCheck.checked = false;
        }
        if (boolR3Pompier == false) {
            R3Pompier.disabled = false;
            R3Pompier.style.cursor = 'pointer';
            R3Pompier.style.color = 'white';
            R3Pompier.style.backgroundColor = 'black';
            R3Pompier.style.top = '0px';
            R3Pompier.style.scale = '1';
            R3PompierCheck.checked = false;
        }
    };
    R3Pompier.onclick = () => {
        R3Pompier.disabled = true;
        R3Pompier.style.cursor = 'not-allowed';
        R3Pompier.style.color = 'black';
        R3Pompier.style.backgroundColor = 'lightgray';
        R3Pompier.style.top = '-10px';
        R3Pompier.style.scale = '1.15';
        R3PompierCheck.checked = true;
        RoutePolice2.disabled = false;
        RoutePolice2.style.cursor = 'pointer';
        if (boolR2Pompier == false) {
            R2Pompier.disabled = false;
            R2Pompier.style.cursor = 'pointer';
            R2Pompier.style.color = 'white';
            R2Pompier.style.backgroundColor = 'black';
            R2Pompier.style.top = '0px';
            R2Pompier.style.scale = '1';
            R2PompierCheck.checked = false;
        }
        if (boolR1Pompier == false) {
            R1Pompier.disabled = false;
            R1Pompier.style.cursor = 'pointer';
            R1Pompier.style.color = 'white';
            R1Pompier.style.backgroundColor = 'black';
            R1Pompier.style.top = '0px';
            R1Pompier.style.scale = '1';
            R1PompierCheck.checked = false;
        }
    };
    /* - routes send PV - */
    RoutePolice.onclick = () => {
        toggle3();
    };
    RoutePompier.onclick = () => {
        toggle4();
    };
    /* - functions desactivate buttons Routes PV - */
    function desactiveR1Police() {
        R1Police.disabled = true;
        R1Police.style.cursor = 'not-allowed';
    }
    function desactiveR2Police() {
        R2Police.disabled = true;
        R2Police.style.cursor = 'not-allowed';
    }
    function desactiveR3Police() {
        R3Police.disabled = true;
        R3Police.style.cursor = 'not-allowed';
    }
    /* - confirm routes buttons - */
    ConfirmRoutePolice.onclick = () => {
        if (R1PoliceCheck.checked == true) {
            boolR1Policier = true;
            desactiveR1Police();
            desactiveR2Police();
            desactiveR3Police();
            service.send(JSON.stringify("Route1PV"));
        }
        if (R2PoliceCheck.checked == true) {
            boolR2Policier = true;
            desactiveR1Police();
            desactiveR2Police();
            desactiveR3Police();
            service.send(JSON.stringify("Route2PV"));
        }
        if (R3PoliceCheck.checked == true) {
            boolR3Policier = true;
            desactiveR1Police();
            desactiveR2Police();
            desactiveR3Police();
            service.send(JSON.stringify("Route3PV"));
        }
        RoutePolice.disabled = true;
        RoutePolice.style.cursor = 'not-allowed';
        RoutePolice.style.color = 'black';
        RoutePolice.style.backgroundColor = 'lightgray';
        RoutePompier.disabled = false;
        RoutePompier.style.cursor = 'pointer';
        toggle3();
    };
    ConfirmRoutePompier.onclick = () => {
        ConfirmRoutePompier.disabled = true;
        RoutePompier.disabled = true;
        RoutePompier.style.top = '0px';
        RoutePompier.style.right = '0px';
        RoutePompier.style.cursor = 'not-allowed';
        RoutePompier.style.color = 'black';
        RoutePompier.style.backgroundColor = 'lightgray';
        RoutePompier.style.boxShadow = 'none';
        RoutePolice.style.top = '0px';
        RoutePolice.style.left = '0px';
        RoutePolice.style.boxShadow = 'none';
        R1Police.style.top = '0px';
        R2Police.style.top = '0px';
        R3Police.style.top = '0px';
        toggle4();
        check3();
        console.log("Firefighter agreed, route PV chosen.");
        if ((R1Police.style.backgroundColor == 'lightgray') && (ConfirmRoutePompier.disabled == true)) {
            service.send(JSON.stringify("AgreeRoute1PV"));
        }
        if ((R2Police.style.backgroundColor == 'lightgray') && (ConfirmRoutePompier.disabled == true)) {
            service.send(JSON.stringify("AgreeRoute2PV"));
        }
        if ((R3Police.style.backgroundColor == 'lightgray') && (ConfirmRoutePompier.disabled == true)) {
            service.send(JSON.stringify("AgreeRoute3PV"));
        }
    };
    /* - cancel routes PV buttons - */
    Cancel3.onclick = () => {
        toggle3();
    };
    Cancel4.onclick = () => {
        /* - simple - */
        if (boolR1Policier == true && boolR2Policier == false && boolR3Policier == false) {
            R1Police.style.top = '0px';
            R1Police.style.backgroundColor = '#a50000';
            R1Police.style.scale = '1';
            R2Police.disabled = false;
            R2Police.style.cursor = 'pointer';
            R3Police.disabled = false;
            R3Police.style.cursor = 'pointer';
            RoutePolice.disabled = true;
            RoutePolice.style.cursor = 'not-allowed';
            RoutePolice.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1PV"));
            console.log("Route 1 for PV refused..");
        }
        if (boolR2Policier == true && boolR1Policier == false && boolR3Policier == false) {
            R2Police.style.top = '0px';
            R2Police.style.backgroundColor = '#a50000';
            R2Police.style.scale = '1';
            R1Police.disabled = false;
            R1Police.style.cursor = 'pointer';
            R3Police.disabled = false;
            R3Police.style.cursor = 'pointer';
            RoutePolice.disabled = true;
            RoutePolice.style.cursor = 'not-allowed';
            RoutePolice.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2PV"));
            console.log("Route 2 for PV refused..");
        }
        if (boolR3Policier == true && boolR2Policier == false && boolR1Policier == false) {
            R3Police.style.top = '0px';
            R3Police.style.backgroundColor = '#a50000';
            R3Police.style.scale = '1';
            R2Police.disabled = false;
            R2Police.style.cursor = 'pointer';
            R1Police.disabled = false;
            R1Police.style.cursor = 'pointer';
            RoutePolice.disabled = true;
            RoutePolice.style.cursor = 'not-allowed';
            RoutePolice.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute3PV"));
            console.log("Route 3 for PV refused..");
        }
        /* - double - */
        if (boolR1Policier == true && boolR2Policier == true && boolR3Policier == false) {
            R1Police.style.top = '0px';
            R1Police.style.backgroundColor = '#a50000';
            R1Police.style.scale = '1';
            R2Police.style.top = '0px';
            R2Police.style.backgroundColor = '#a50000';
            R2Police.style.scale = '1';
            R3Police.disabled = false;
            R3Police.style.cursor = 'pointer';
            RoutePolice.disabled = true;
            RoutePolice.style.cursor = 'not-allowed';
            RoutePolice.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_2PV"));
            console.log("Routes 1 and 2 for PV refused..");
        }
        if (boolR3Policier == true && boolR2Policier == true && boolR1Policier == false) {
            R3Police.style.top = '0px';
            R3Police.style.backgroundColor = '#a50000';
            R3Police.style.scale = '1';
            R2Police.style.top = '0px';
            R2Police.style.backgroundColor = '#a50000';
            R2Police.style.scale = '1';
            R1Police.disabled = false;
            R1Police.style.cursor = 'pointer';
            RoutePolice.disabled = true;
            RoutePolice.style.cursor = 'not-allowed';
            RoutePolice.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2_3PV"));
            console.log("Routes 2 and 3 for PV refused..");
        }
        if (boolR3Policier == true && boolR1Policier == true && boolR2Policier == false) {
            R3Police.style.top = '0px';
            R3Police.style.backgroundColor = '#a50000';
            R3Police.style.scale = '1';
            R1Police.style.top = '0px';
            R1Police.style.backgroundColor = '#a50000';
            R1Police.style.scale = '1';
            R2Police.disabled = false;
            R2Police.style.cursor = 'pointer';
            RoutePolice.disabled = true;
            RoutePolice.style.cursor = 'not-allowed';
            RoutePolice.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_3PV"));
            console.log("Routes 1 and 3 for PV refused..");
        }
        /* - triple - */
        if (boolR3Policier == true && boolR1Policier == true && boolR2Policier == true) {
            R3Police.style.top = '0px';
            R3Police.style.backgroundColor = '#a50000';
            R3Police.style.scale = '1';
            R1Police.style.top = '0px';
            R1Police.style.backgroundColor = '#a50000';
            R1Police.style.scale = '1';
            R2Police.style.top = '0px';
            R2Police.style.backgroundColor = '#a50000';
            R2Police.style.scale = '1';
            RoutePolice.disabled = true;
            RoutePolice.style.cursor = 'not-allowed';
            RoutePolice.style.backgroundColor = 'lightgray';
            RoutePolice.style.top = '0px';
            RoutePolice.style.left = '0px';
            RoutePompier.style.top = '0px';
            RoutePompier.style.right = '0px';
            RoutePompier.style.backgroundColor = 'lightgray';
            ConfirmRoutePompier.disabled = true;
            console.log("All routes refused for PV, no more routes left.");
            service.send(JSON.stringify("NoMoreRouteLeftPV"));
        }
        RoutePompier.disabled = true;
        RoutePompier.style.cursor = 'not-allowed';
        toggle4();
        check3();
    };
    /* - routes send FT - */
    RoutePolice2.onclick = () => {
        toggle5();
    };
    RoutePompier2.onclick = () => {
        toggle6();
    };
    /* - functions desactivate buttons Routes FT - */
    function desactiveR1Pompier() {
        R1Pompier.disabled = true;
        R1Pompier.style.cursor = 'not-allowed';
    }
    function desactiveR2Pompier() {
        R2Pompier.disabled = true;
        R2Pompier.style.cursor = 'not-allowed';
    }
    function desactiveR3Pompier() {
        R3Pompier.disabled = true;
        R3Pompier.style.cursor = 'not-allowed';
    }
    /* - confirm routes buttons - */
    ConfirmRoutePolice2.onclick = () => {
        if (R1PompierCheck.checked == true) {
            boolR1Pompier = true;
            desactiveR1Pompier();
            desactiveR2Pompier();
            desactiveR3Pompier();
            service.send(JSON.stringify("Route1FT"));
        }
        if (R2PompierCheck.checked == true) {
            boolR2Pompier = true;
            desactiveR1Pompier();
            desactiveR2Pompier();
            desactiveR3Pompier();
            service.send(JSON.stringify("Route2FT"));
        }
        if (R3PompierCheck.checked == true) {
            boolR3Pompier = true;
            desactiveR1Pompier();
            desactiveR2Pompier();
            desactiveR3Pompier();
            service.send(JSON.stringify("Route3FT"));
        }
        RoutePolice2.disabled = true;
        RoutePolice2.style.top = '0px';
        RoutePolice2.style.left = '0px';
        RoutePolice2.style.cursor = 'not-allowed';
        RoutePolice2.style.color = 'black';
        RoutePolice2.style.backgroundColor = 'lightgray';
        RoutePompier2.disabled = false;
        RoutePompier2.style.cursor = 'pointer';
        toggle5();
    };
    ConfirmRoutePompier2.onclick = () => {
        ConfirmRoutePompier2.disabled = true;
        RoutePompier2.disabled = true;
        RoutePompier2.style.top = '0px';
        RoutePompier2.style.right = '0px';
        RoutePompier2.style.cursor = 'not-allowed';
        RoutePompier2.style.color = 'black';
        RoutePompier2.style.backgroundColor = 'lightgray';
        RoutePompier2.style.boxShadow = 'none';
        RoutePolice2.style.top = '0px';
        RoutePolice2.style.left = '0px';
        RoutePolice2.style.boxShadow = 'none';
        R1Pompier.style.top = '0px';
        R2Pompier.style.top = '0px';
        R3Pompier.style.top = '0px';
        toggle6();
        check3();
        console.log("Firefighters agreed, route FT chosen.");
        if ((R1Pompier.style.backgroundColor == 'lightgray') && (ConfirmRoutePompier2.disabled == true)) {
            service.send(JSON.stringify("AgreeRoute1FT"));
        }
        if ((R2Pompier.style.backgroundColor == 'lightgray') && (ConfirmRoutePompier2.disabled == true)) {
            service.send(JSON.stringify("AgreeRoute2FT"));
        }
        if ((R3Pompier.style.backgroundColor == 'lightgray') && (ConfirmRoutePompier2.disabled == true)) {
            service.send(JSON.stringify("AgreeRoute3FT"));
        }
    };
    /* - cancel routes FT buttons - */
    Cancel5.onclick = () => {
        toggle5();
    };
    Cancel6.onclick = () => {
        /* - simple - */
        if (boolR1Pompier == true && boolR2Pompier == false && boolR3Pompier == false) {
            R1Pompier.style.top = '0px';
            R1Pompier.style.backgroundColor = '#a50000';
            R1Pompier.style.scale = '1';
            R2Pompier.disabled = false;
            R2Pompier.style.cursor = 'pointer';
            R3Pompier.disabled = false;
            R3Pompier.style.cursor = 'pointer';
            RoutePolice2.disabled = true;
            RoutePolice2.style.cursor = 'not-allowed';
            RoutePolice2.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1FT"));
            console.log("Route 1 for FT refused..");
        }
        if (boolR2Pompier == true && boolR1Pompier == false && boolR3Pompier == false) {
            R2Pompier.style.top = '0px';
            R2Pompier.style.backgroundColor = '#a50000';
            R2Pompier.style.scale = '1';
            R1Pompier.disabled = false;
            R1Pompier.style.cursor = 'pointer';
            R3Pompier.disabled = false;
            R3Pompier.style.cursor = 'pointer';
            RoutePolice2.disabled = true;
            RoutePolice2.style.cursor = 'not-allowed';
            RoutePolice2.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2FT"));
            console.log("Route 2 for FT refused..");
        }
        if (boolR3Pompier == true && boolR2Pompier == false && boolR1Pompier == false) {
            R3Pompier.style.top = '0px';
            R3Pompier.style.backgroundColor = '#a50000';
            R3Pompier.style.scale = '1';
            R2Pompier.disabled = false;
            R2Pompier.style.cursor = 'pointer';
            R1Pompier.disabled = false;
            R1Pompier.style.cursor = 'pointer';
            RoutePolice2.disabled = true;
            RoutePolice2.style.cursor = 'not-allowed';
            RoutePolice2.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute3FT"));
            console.log("Route 3 for FT refused..");
        }
        /* - double - */
        if (boolR1Pompier == true && boolR2Pompier == true && boolR3Pompier == false) {
            R1Pompier.style.top = '0px';
            R1Pompier.style.backgroundColor = '#a50000';
            R1Pompier.style.scale = '1';
            R2Pompier.style.top = '0px';
            R2Pompier.style.backgroundColor = '#a50000';
            R2Pompier.style.scale = '1';
            R3Pompier.disabled = false;
            R3Pompier.style.cursor = 'pointer';
            RoutePolice2.disabled = true;
            RoutePolice2.style.cursor = 'not-allowed';
            RoutePolice2.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_2FT"));
            console.log("Routes 1 and 2 for FT refused..");
        }
        if (boolR3Pompier == true && boolR2Pompier == true && boolR1Pompier == false) {
            R3Pompier.style.top = '0px';
            R3Pompier.style.backgroundColor = '#a50000';
            R3Pompier.style.scale = '1';
            R2Pompier.style.top = '0px';
            R2Pompier.style.backgroundColor = '#a50000';
            R2Pompier.style.scale = '1';
            R1Pompier.disabled = false;
            R1Pompier.style.cursor = 'pointer';
            RoutePolice2.disabled = true;
            RoutePolice2.style.cursor = 'not-allowed';
            RoutePolice2.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2_3FT"));
            console.log("Routes 2 and 3 for FT refused..");
        }
        if (boolR3Pompier == true && boolR1Pompier == true && boolR2Pompier == false) {
            R3Pompier.style.top = '0px';
            R3Pompier.style.backgroundColor = '#a50000';
            R3Pompier.style.scale = '1';
            R1Pompier.style.top = '0px';
            R1Pompier.style.backgroundColor = '#a50000';
            R1Pompier.style.scale = '1';
            R2Pompier.disabled = false;
            R2Pompier.style.cursor = 'pointer';
            RoutePolice2.disabled = true;
            RoutePolice2.style.cursor = 'not-allowed';
            RoutePolice2.style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_3FT"));
            console.log("Routes 1 and 3 for FT refused..");
        }
        /* - triple - */
        if (boolR3Pompier == true && boolR1Pompier == true && boolR2Pompier == true) {
            R3Pompier.style.top = '0px';
            R3Pompier.style.backgroundColor = '#a50000';
            R3Pompier.style.scale = '1';
            R1Pompier.style.top = '0px';
            R1Pompier.style.backgroundColor = '#a50000';
            R1Pompier.style.scale = '1';
            R2Pompier.style.top = '0px';
            R2Pompier.style.backgroundColor = '#a50000';
            R2Pompier.style.scale = '1';
            RoutePolice2.disabled = true;
            RoutePolice2.style.cursor = 'not-allowed';
            RoutePolice2.style.backgroundColor = 'lightgray';
            RoutePolice2.style.top = '0px';
            RoutePolice2.style.left = '0px';
            RoutePompier2.style.top = '0px';
            RoutePompier2.style.right = '0px';
            RoutePompier2.style.backgroundColor = 'lightgray';
            ConfirmRoutePompier2.disabled = true;
            console.log("All routes refused for FT, no more routes left.");
            service.send(JSON.stringify("NoMoreRouteLeftFT"));
        }
        RoutePompier2.disabled = true;
        RoutePompier2.style.cursor = 'not-allowed';
        toggle6();
        check3();
    };
    /* - check connection function to unlock Step 4 */
    function check3() {
        if ((ConfirmRoutePompier.disabled == true) && (ConfirmRoutePompier2.disabled == true)) {
            Etape4.style.visibility = "visible";
            Icon7.style.visibility = "visible";
            Icon8.style.visibility = "visible";
            Instructions4.style.visibility = "visible";
            Instructions4_2.style.visibility = "visible";
            PVsent.style.visibility = "visible";
            FTsent.style.visibility = "visible";
        }
    }
    /* - step 4 - */
    /* - dispatch buttons - */
    PVsent.onclick = () => {
        let nbVehiclesPolicier = document.getElementById("nbVehiclesPolicier").value;
        if (nbVehiclesPolicier == "1") {
            document.getElementById("PVvehicles1").innerHTML = "PV#1";
            document.getElementById("PVvehicles2").style.display = "none";
            document.getElementById("PVvehicles3").style.display = "none";
            document.getElementById("jump1_1").style.display = "none";
            document.getElementById("jump2_1").style.display = "none";
        }
        if (nbVehiclesPolicier == "2") {
            document.getElementById("PVvehicles1").innerHTML = "PV#1";
            document.getElementById("PVvehicles2").innerHTML = "PV#2";
            document.getElementById("PVvehicles3").style.display = "none";
            document.getElementById("jump2_1").style.display = "none";
        }
        if (nbVehiclesPolicier == "3") {
            document.getElementById("PVvehicles1").innerHTML = "PV#1";
            document.getElementById("PVvehicles2").innerHTML = "PV#2";
            document.getElementById("PVvehicles3").innerHTML = "PV#3";
        }
        console.log("number of PV dispatched : " + nbVehiclesPolicier);
        toggle7();
    };
    ConfirmDispatchPV.onclick = () => {
        PVsent.style.cursor = 'not-allowed';
        PVsent.style.backgroundColor = 'lightgray';
        PVsent.style.top = '0px';
        PVsent.style.left = '0px';
        PVsent.disabled = true;
        PVsent.style.boxShadow = "none";
        service.send(JSON.stringify("AllVehiclesDispatchedPV"));
        toggle7();
        check4();
    };
    FTsent.onclick = () => {
        let nbVehiclesPompier = document.getElementById("nbVehiclesPompier").value;
        if (nbVehiclesPompier == "1") {
            document.getElementById("FTvehicles1").innerHTML = "FT#1";
            document.getElementById("FTvehicles2").style.display = "none";
            document.getElementById("FTvehicles3").style.display = "none";
            document.getElementById("FTvehicles4").style.display = "none";
            document.getElementById("jump1").style.display = "none";
            document.getElementById("jump2").style.display = "none";
            document.getElementById("jump3").style.display = "none";
        }
        if (nbVehiclesPompier == "2") {
            document.getElementById("FTvehicles1").innerHTML = "FT#1";
            document.getElementById("FTvehicles2").innerHTML = "FT#2";
            document.getElementById("FTvehicles3").style.display = "none";
            document.getElementById("FTvehicles4").style.display = "none";
            document.getElementById("jump2").style.display = "none";
            document.getElementById("jump3").style.display = "none";
        }
        if (nbVehiclesPompier == "3") {
            document.getElementById("FTvehicles1").innerHTML = "FT#1";
            document.getElementById("FTvehicles2").innerHTML = "FT#2";
            document.getElementById("FTvehicles3").innerHTML = "FT#3";
            document.getElementById("FTvehicles4").style.display = "none";
            document.getElementById("jump3").style.display = "none";
        }
        if (nbVehiclesPompier == "4") {
            document.getElementById("FTvehicles1").innerHTML = "FT#1";
            document.getElementById("FTvehicles2").innerHTML = "FT#2";
            document.getElementById("FTvehicles3").innerHTML = "FT#3";
            document.getElementById("FTvehicles4").innerHTML = "FT#4";
        }
        console.log("number of FT dispatched : " + nbVehiclesPompier);
        toggle8();
    };
    ConfirmDispatchFT.onclick = () => {
        FTsent.style.cursor = 'not-allowed';
        FTsent.style.backgroundColor = 'lightgray';
        FTsent.style.top = '0px';
        FTsent.style.right = '0px';
        FTsent.disabled = true;
        FTsent.style.boxShadow = "none";
        service.send(JSON.stringify("AllVehiclesDispatchedFT"));
        toggle8();
        check4();
    };
    /* - check connection function to unlock Step 5_1 */
    function check4() {
        if ((PVsent.disabled == true) && (FTsent.disabled == true)) {
            Etape5.style.visibility = "visible";
            Icon9.style.visibility = "visible";
            Icon10.style.visibility = "visible";
            Instructions5.style.visibility = "visible";
            Instructions5_2.style.visibility = "visible";
            PVbreakdown.style.visibility = "visible";
            FTbreakdown.style.visibility = "visible";
        }
    }
    /* - step 5 - */
    /* - breakdown buttons - */
    PVbreakdown.onclick = () => {
        ConfirmBreakdownPV.disabled = true;
        ConfirmBreakdownPV.style.cursor = "not-allowed";
        ConfirmBreakdownPV_.disabled = true;
        ConfirmBreakdownPV_.style.cursor = "not-allowed";
        let nbVehiclesPolicier = document.getElementById("nbVehiclesPolicier").value;
        if (nbVehiclesPolicier == "1") {
            document.getElementById("PV2Police").style.display = "none";
            document.getElementById("PV3Police").style.display = "none";
        }
        if (nbVehiclesPolicier == "2") {
            document.getElementById("PV3Police").style.display = "none";
        }
        toggle9();
    };
    CancelSelectionPV.onclick = () => {
        PV1Police.disabled = false;
        PV1Police.style.backgroundColor = "#000000c2";
        PV1Police.style.color = "white";
        PV1Police.style.scale = '1';
        PV1Police.style.cursor = 'pointer';
        PV2Police.disabled = false;
        PV2Police.style.backgroundColor = "#000000c2";
        PV2Police.style.color = "white";
        PV2Police.style.scale = '1';
        PV2Police.style.cursor = 'pointer';
        PV3Police.disabled = false;
        PV3Police.style.backgroundColor = "#000000c2";
        PV3Police.style.color = "white";
        PV3Police.style.scale = '1';
        PV3Police.style.cursor = 'pointer';
    };
    PV1Police.onclick = () => {
        ConfirmBreakdownPV.disabled = false;
        ConfirmBreakdownPV.style.cursor = "pointer";
        ConfirmBreakdownPV_.disabled = false;
        ConfirmBreakdownPV_.style.cursor = "pointer";
        PV1Police.disabled = true;
        PV1Police.style.backgroundColor = "lightgray";
        PV1Police.style.color = "black";
        PV1Police.style.scale = '1.15';
        PV1Police.style.cursor = 'not-allowed';
    };
    PV2Police.onclick = () => {
        ConfirmBreakdownPV.disabled = false;
        ConfirmBreakdownPV.style.cursor = "pointer";
        ConfirmBreakdownPV_.disabled = false;
        ConfirmBreakdownPV_.style.cursor = "pointer";
        PV2Police.disabled = true;
        PV2Police.style.backgroundColor = "lightgray";
        PV2Police.style.color = "black";
        PV2Police.style.scale = '1.15';
        PV2Police.style.cursor = 'not-allowed';
    };
    PV3Police.onclick = () => {
        ConfirmBreakdownPV.disabled = false;
        ConfirmBreakdownPV.style.cursor = "pointer";
        ConfirmBreakdownPV_.disabled = false;
        ConfirmBreakdownPV_.style.cursor = "pointer";
        PV3Police.disabled = true;
        PV3Police.style.backgroundColor = "lightgray";
        PV3Police.style.color = "black";
        PV3Police.style.scale = '1.15';
        PV3Police.style.cursor = 'not-allowed';
    };
    Cancel7.onclick = () => {
        PVbreakdown.disabled = true;
        PVbreakdown.style.backgroundColor = "lightgray";
        PVbreakdown.style.top = "0px";
        PVbreakdown.style.left = "0px";
        PVbreakdown.style.boxShadow = "none";
        PVbreakdown.style.cursor = "not-allowed";
        PVbreakdown.disabled = true;
        toggle9();
        check5();
    };
    ConfirmBreakdownPV.onclick = () => {
        if (PV1Police.disabled == true) {
            let nb = "PoliceVehicleBreakdown " + "1";
            service.send(JSON.stringify(nb));
        }
        if (PV2Police.disabled == true) {
            let nb = "PoliceVehicleBreakdown " + "2";
            service.send(JSON.stringify(nb));
        }
        if (PV3Police.disabled == true) {
            let nb = "PoliceVehicleBreakdown " + "3";
            service.send(JSON.stringify(nb));
        }
        if ((PV3Police.disabled == true) || (PV1Police.disabled == true) || (PV2Police.disabled == true)) {
            PVbreakdown.disabled = true;
            PVbreakdown.style.backgroundColor = "lightgray";
            PVbreakdown.style.top = "0px";
            PVbreakdown.style.left = "0px";
            PVbreakdown.style.boxShadow = "none";
            PVbreakdown.style.cursor = "not-allowed";
            toggle9();
            check5();
        }
    };
    FTbreakdown.onclick = () => {
        ConfirmBreakdownFT.disabled = true;
        ConfirmBreakdownFT.style.cursor = "not-allowed";
        ConfirmBreakdownFT_.disabled = true;
        ConfirmBreakdownFT_.style.cursor = "not-allowed";
        let nbVehiclesPompier = document.getElementById("nbVehiclesPompier").value;
        if (nbVehiclesPompier == "1") {
            document.getElementById("FT2Pompier").style.display = "none";
            document.getElementById("FT3Pompier").style.display = "none";
            document.getElementById("FT4Pompier").style.display = "none";
        }
        if (nbVehiclesPompier == "2") {
            document.getElementById("FT3Pompier").style.display = "none";
            document.getElementById("FT4Pompier").style.display = "none";
        }
        if (nbVehiclesPompier == "3") {
            document.getElementById("FT4Pompier").style.display = "none";
        }
        toggle10();
    };
    CancelSelectionFT.onclick = () => {
        FT1Pompier.disabled = false;
        FT1Pompier.style.backgroundColor = "#000000c2";
        FT1Pompier.style.scale = '1';
        FT1Pompier.style.color = "white";
        FT1Pompier.style.cursor = "pointer";
        FT2Pompier.disabled = false;
        FT2Pompier.style.backgroundColor = "#000000c2";
        FT2Pompier.style.scale = '1';
        FT2Pompier.style.color = "white";
        FT2Pompier.style.cursor = "pointer";
        FT3Pompier.disabled = false;
        FT3Pompier.style.backgroundColor = "#000000c2";
        FT3Pompier.style.color = "white";
        FT3Pompier.style.cursor = "pointer";
        FT3Pompier.style.scale = '1';
        FT4Pompier.disabled = false;
        FT4Pompier.style.backgroundColor = "#000000c2";
        FT4Pompier.style.color = "white";
        FT4Pompier.style.cursor = "pointer";
        FT4Pompier.style.scale = '1';
    };
    FT1Pompier.onclick = () => {
        ConfirmBreakdownFT.disabled = false;
        ConfirmBreakdownFT.style.cursor = "pointer";
        ConfirmBreakdownFT_.disabled = false;
        ConfirmBreakdownFT_.style.cursor = "pointer";
        FT1Pompier.disabled = true;
        FT1Pompier.style.backgroundColor = "lightgray";
        FT1Pompier.style.color = "black";
        FT1Pompier.style.scale = '1.15';
        FT1Pompier.style.cursor = 'not-allowed';
    };
    FT2Pompier.onclick = () => {
        ConfirmBreakdownFT.disabled = false;
        ConfirmBreakdownFT.style.cursor = "pointer";
        ConfirmBreakdownFT_.disabled = false;
        ConfirmBreakdownFT_.style.cursor = "pointer";
        FT2Pompier.disabled = true;
        FT2Pompier.style.backgroundColor = "lightgray";
        FT2Pompier.style.color = "black";
        FT2Pompier.style.scale = '1.15';
        FT2Pompier.style.cursor = 'not-allowed';
    };
    FT3Pompier.onclick = () => {
        ConfirmBreakdownFT.disabled = false;
        ConfirmBreakdownFT.style.cursor = "pointer";
        ConfirmBreakdownFT_.disabled = false;
        ConfirmBreakdownFT_.style.cursor = "pointer";
        FT3Pompier.disabled = true;
        FT3Pompier.style.backgroundColor = "lightgray";
        FT3Pompier.style.color = "black";
        FT3Pompier.style.scale = '1.15';
        FT3Pompier.style.cursor = 'not-allowed';
    };
    FT4Pompier.onclick = () => {
        ConfirmBreakdownFT.disabled = false;
        ConfirmBreakdownFT.style.cursor = "pointer";
        ConfirmBreakdownFT_.disabled = false;
        ConfirmBreakdownFT_.style.cursor = "pointer";
        FT4Pompier.disabled = true;
        FT4Pompier.style.backgroundColor = "lightgray";
        FT4Pompier.style.color = "black";
        FT4Pompier.style.scale = '1.15';
        FT4Pompier.style.cursor = 'not-allowed';
    };
    ConfirmBreakdownFT.onclick = () => {
        if (FT1Pompier.disabled == true) {
            let nb = "PompierVehicleBreakdown " + "1";
            service.send(JSON.stringify(nb));
        }
        if (FT2Pompier.disabled == true) {
            let nb = "PompierVehicleBreakdown " + "2";
            service.send(JSON.stringify(nb));
        }
        if (FT3Pompier.disabled == true) {
            let nb = "PompierVehicleBreakdown " + "3";
            service.send(JSON.stringify(nb));
        }
        if (FT4Pompier.disabled == true) {
            let nb = "PompierVehicleBreakdown " + "4";
            service.send(JSON.stringify(nb));
        }
        if ((FT1Pompier.disabled == true) || (FT2Pompier.disabled == true) || (FT3Pompier.disabled == true) || (FT4Pompier.disabled == true)) {
            FTbreakdown.disabled = true;
            FTbreakdown.style.backgroundColor = "lightgray";
            FTbreakdown.style.top = "0px";
            FTbreakdown.style.right = "0px";
            FTbreakdown.style.boxShadow = "none";
            FTbreakdown.style.cursor = "not-allowed";
            toggle10();
            check5();
        }
    };
    Cancel8.onclick = () => {
        FTbreakdown.disabled = true;
        FTbreakdown.style.backgroundColor = "lightgray";
        FTbreakdown.style.top = "0px";
        FTbreakdown.style.right = "0px";
        FTbreakdown.style.boxShadow = "none";
        FTbreakdown.style.cursor = "not-allowed";
        toggle10();
        check5();
    };
    /* - check connection function to unlock Step 5_2 */
    function check5() {
        if ((PVbreakdown.style.backgroundColor == "lightgray") && (FTbreakdown.style.backgroundColor == "lightgray")) {
            CrisisMoreSevere.style.visibility = "visible";
            CrisisLessSevere.style.visibility = "visible";
            Icon13.style.visibility = "visible";
            Icon13_2.style.visibility = "visible";
            Icon13_3.style.visibility = "visible";
            Icon13_4.style.visibility = "visible";
            blocked_vehicle.style.visibility = "visible";
            RAS.style.visibility = "visible";
        }
    }
    /* - step 5_2 - */
    /* - blocked, crisis state buttons - */
    CrisisMoreSevere.onclick = () => {
        service.send(JSON.stringify("CrisisMoreSevere"));
        PolicierVehicles.disabled = false;
        PompierVehicles.disabled = false;
        RoutePolice.disabled = false;
        RoutePolice2.disabled = false;
        RoutePompier.disabled = false;
        RoutePompier2.disabled = false;
        R1Police.disabled = false;
        R2Police.disabled = false;
        R3Police.disabled = false;
        R1Pompier.disabled = false;
        R2Pompier.disabled = false;
        R3Pompier.disabled = false;
        ConfirmRoutePompier.disabled = false;
        ConfirmRoutePompier2.disabled = false;
        ConfirmRoutePolice.disabled = false;
        PVsent.disabled = false;
        FTsent.disabled = false;
        PVbreakdown.disabled = false;
        FTbreakdown.disabled = false;
        CrisisMoreSevere.disabled = false;
        blocked_vehicle.disabled = false;
        RAS.disabled = false;
        CrisisLessSevere.disabled = false;
        PolicierVehicles.style.backgroundColor = "#1cb182";
        PompierVehicles.style.backgroundColor = "#1cb182";
        RoutePolice.style.backgroundColor = "#1cb182";
        RoutePolice2.style.backgroundColor = "#1cb182";
        RoutePompier.style.backgroundColor = "#1cb182";
        RoutePompier2.style.backgroundColor = "#1cb182";
        R1Police.style.backgroundColor = "black";
        R2Police.style.backgroundColor = "black";
        R3Police.style.backgroundColor = "black";
        R1Pompier.style.backgroundColor = "black";
        R2Pompier.style.backgroundColor = "black";
        R3Pompier.style.backgroundColor = "black";
        ConfirmRoutePompier.style.backgroundColor = "#1cb182";
        ConfirmRoutePompier2.style.backgroundColor = "#1cb182";
        ConfirmRoutePolice.style.backgroundColor = "#1cb182";
        PVsent.style.backgroundColor = "#1cb182";
        FTsent.style.backgroundColor = "#1cb182";
        PVbreakdown.style.backgroundColor = "#1cb182";
        FTbreakdown.style.backgroundColor = "#1cb182";
        CrisisMoreSevere.style.backgroundColor = "#1cb182";
        blocked_vehicle.style.backgroundColor = "#1cb182";
        RAS.style.backgroundColor = "#1cb182";
        CrisisLessSevere.style.backgroundColor = "#1cb182";
        PolicierVehicles.style.color = "black";
        PompierVehicles.style.color = "black";
        RoutePolice.style.color = "black";
        RoutePolice2.style.color = "black";
        RoutePompier.style.color = "black";
        RoutePompier2.style.color = "black";
        R1Police.style.color = "white";
        R2Police.style.color = "white";
        R3Police.style.color = "white";
        R1Pompier.style.color = "white";
        R2Pompier.style.color = "white";
        R3Pompier.style.color = "white";
        ConfirmRoutePompier.style.color = "black";
        ConfirmRoutePompier2.style.color = "black";
        ConfirmRoutePolice.style.color = "black";
        PVsent.style.color = "black";
        FTsent.style.color = "black";
        PVbreakdown.style.color = "black";
        FTbreakdown.style.color = "black";
        CrisisMoreSevere.style.color = "black";
        blocked_vehicle.style.color = "black";
        RAS.style.color = "black";
        CrisisLessSevere.style.color = "black";
        PolicierVehicles.style.cursor = "pointer";
        PompierVehicles.style.cursor = "pointer";
        R1Police.style.cursor = "pointer";
        R2Police.style.cursor = "pointer";
        R3Police.style.cursor = "pointer";
        R1Pompier.style.cursor = "pointer";
        R2Pompier.style.cursor = "pointer";
        R3Pompier.style.cursor = "pointer";
        ConfirmRoutePompier.style.cursor = "pointer";
        ConfirmRoutePompier2.style.cursor = "pointer";
        ConfirmRoutePolice.style.cursor = "pointer";
        PVsent.style.cursor = "pointer";
        FTsent.style.cursor = "pointer";
        PVbreakdown.style.cursor = "pointer";
        FTbreakdown.style.cursor = "pointer";
        CrisisMoreSevere.style.cursor = "pointer";
        blocked_vehicle.style.cursor = "pointer";
        RAS.style.cursor = "pointer";
        CrisisLessSevere.style.cursor = "pointer";
        Etape3.style.visibility = 'hidden';
        Etape3_2.style.visibility = 'hidden';
        Icon5.style.visibility = 'hidden';
        Icon5_2.style.visibility = 'hidden';
        Icon6.style.visibility = 'hidden';
        Icon6_2.style.visibility = 'hidden';
        Instructions2.style.visibility = 'hidden';
        Instructions2_2.style.visibility = 'hidden';
        Instructions3.style.visibility = 'hidden';
        Instructions3_2.style.visibility = 'hidden';
        RoutePolice.style.visibility = 'hidden';
        RoutePompier.style.visibility = 'hidden';
        RoutePolice2.style.visibility = 'hidden';
        RoutePompier2.style.visibility = 'hidden';
        R1Police.style.visibility = 'hidden';
        R2Police.style.visibility = 'hidden';
        R3Police.style.visibility = 'hidden';
        R1Pompier.style.visibility = 'hidden';
        R2Pompier.style.visibility = 'hidden';
        R3Pompier.style.visibility = 'hidden';
        Etape4.style.visibility = "hidden";
        Icon7.style.visibility = "hidden";
        Icon8.style.visibility = "hidden";
        Instructions4.style.visibility = "hidden";
        Instructions4_2.style.visibility = "hidden";
        PVsent.style.visibility = "hidden";
        FTsent.style.visibility = "hidden";
        Etape5.style.visibility = "hidden";
        Icon9.style.visibility = "hidden";
        Icon10.style.visibility = "hidden";
        Instructions5.style.visibility = "hidden";
        Instructions5_2.style.visibility = "hidden";
        PVbreakdown.style.visibility = "hidden";
        FTbreakdown.style.visibility = "hidden";
        CrisisMoreSevere.style.visibility = "hidden";
        CrisisLessSevere.style.visibility = "hidden";
        Icon13.style.visibility = "hidden";
        Icon13_2.style.visibility = "hidden";
        Icon13_3.style.visibility = "hidden";
        Icon13_4.style.visibility = "hidden";
        blocked_vehicle.style.visibility = "hidden";
        RAS.style.visibility = "hidden";
    };
    blocked_vehicle.onclick = () => {
        ConfirmBlockedPV.disabled = true;
        ConfirmBlockedFT.disabled = true;
        ConfirmBlockedPV.style.backgroundColor = "#1cb182";
        ConfirmBlockedFT.style.backgroundColor = "#1cb182";
        PVPoliceBlocked.style.backgroundColor = "black";
        PVPoliceBlocked.style.cursor = "cursor";
        PVPoliceBlocked.disabled = false;
        FTPompierBlocked.style.backgroundColor = "black";
        FTPompierBlocked.style.cursor = "cursor";
        FTPompierBlocked.disabled = false;
        toggle13();
    };
    PVPoliceBlocked.onclick = () => {
        PVPoliceBlocked.style.backgroundColor = "lightgray";
        PVPoliceBlocked.style.cursor = "not-allowed";
        PVPoliceBlocked.disabled = true;
        ConfirmBlockedPV.disabled = false;
    };
    FTPompierBlocked.onclick = () => {
        FTPompierBlocked.style.backgroundColor = "lightgray";
        FTPompierBlocked.style.cursor = "not-allowed";
        FTPompierBlocked.disabled = true;
        ConfirmBlockedPV.disabled = false;
    };
    ConfirmBlockedPV.onclick = () => {
        ConfirmBlockedPV.style.backgroundColor = "lightgray";
        ConfirmBlockedPV.style.cursor = "not-allowed";
        ConfirmBlockedPV.disabled = true;
        service.send(JSON.stringify("VehiculeBlockedPV"));
        check8();
    };
    ConfirmBlockedFT.onclick = () => {
        ConfirmBlockedFT.disabled = true;
        ConfirmBlockedFT.style.backgroundColor = "lightgray";
        ConfirmBlockedFT.style.cursor = "not-allowed";
        service.send(JSON.stringify("VehiculeBlockedFT"));
        check8();
    };
    function check8() {
        if ((ConfirmBlockedPV.style.backgroundColor == "lightgray") && (ConfirmBlockedFT.style.backgroundColor == "lightgray")) {
            toggle13();
            PolicierVehicles.disabled = false;
            PompierVehicles.disabled = false;
            RoutePolice.disabled = false;
            RoutePolice2.disabled = false;
            RoutePompier.disabled = false;
            RoutePompier2.disabled = false;
            R1Police.disabled = false;
            R2Police.disabled = false;
            R3Police.disabled = false;
            R1Pompier.disabled = false;
            R2Pompier.disabled = false;
            R3Pompier.disabled = false;
            ConfirmRoutePompier.disabled = false;
            ConfirmRoutePompier2.disabled = false;
            ConfirmRoutePolice.disabled = false;
            PVsent.disabled = false;
            FTsent.disabled = false;
            PVbreakdown.disabled = false;
            FTbreakdown.disabled = false;
            CrisisMoreSevere.disabled = false;
            blocked_vehicle.disabled = false;
            RAS.disabled = false;
            CrisisLessSevere.disabled = false;
            PolicierVehicles.style.backgroundColor = "#1cb182";
            PompierVehicles.style.backgroundColor = "#1cb182";
            RoutePolice.style.backgroundColor = "#1cb182";
            RoutePolice2.style.backgroundColor = "#1cb182";
            RoutePompier.style.backgroundColor = "#1cb182";
            RoutePompier2.style.backgroundColor = "#1cb182";
            R1Police.style.backgroundColor = "black";
            R2Police.style.backgroundColor = "black";
            R3Police.style.backgroundColor = "black";
            R1Pompier.style.backgroundColor = "black";
            R2Pompier.style.backgroundColor = "black";
            R3Pompier.style.backgroundColor = "black";
            ConfirmRoutePompier.style.backgroundColor = "#1cb182";
            ConfirmRoutePompier2.style.backgroundColor = "#1cb182";
            ConfirmRoutePolice.style.backgroundColor = "#1cb182";
            PVsent.style.backgroundColor = "#1cb182";
            FTsent.style.backgroundColor = "#1cb182";
            PVbreakdown.style.backgroundColor = "#1cb182";
            FTbreakdown.style.backgroundColor = "#1cb182";
            CrisisMoreSevere.style.backgroundColor = "#1cb182";
            blocked_vehicle.style.backgroundColor = "#1cb182";
            RAS.style.backgroundColor = "#1cb182";
            CrisisLessSevere.style.backgroundColor = "#1cb182";
            PolicierVehicles.style.color = "black";
            PompierVehicles.style.color = "black";
            RoutePolice.style.color = "black";
            RoutePolice2.style.color = "black";
            RoutePompier.style.color = "black";
            RoutePompier2.style.color = "black";
            R1Police.style.color = "white";
            R2Police.style.color = "white";
            R3Police.style.color = "white";
            R1Pompier.style.color = "white";
            R2Pompier.style.color = "white";
            R3Pompier.style.color = "white";
            ConfirmRoutePompier.style.color = "black";
            ConfirmRoutePompier2.style.color = "black";
            ConfirmRoutePolice.style.color = "black";
            PVsent.style.color = "black";
            FTsent.style.color = "black";
            PVbreakdown.style.color = "black";
            FTbreakdown.style.color = "black";
            CrisisMoreSevere.style.color = "black";
            blocked_vehicle.style.color = "black";
            RAS.style.color = "black";
            CrisisLessSevere.style.color = "black";
            PolicierVehicles.style.cursor = "pointer";
            PompierVehicles.style.cursor = "pointer";
            R1Police.style.cursor = "pointer";
            R2Police.style.cursor = "pointer";
            R3Police.style.cursor = "pointer";
            R1Pompier.style.cursor = "pointer";
            R2Pompier.style.cursor = "pointer";
            R3Pompier.style.cursor = "pointer";
            ConfirmRoutePompier.style.cursor = "pointer";
            ConfirmRoutePompier2.style.cursor = "pointer";
            ConfirmRoutePolice.style.cursor = "pointer";
            PVsent.style.cursor = "pointer";
            FTsent.style.cursor = "pointer";
            PVbreakdown.style.cursor = "pointer";
            FTbreakdown.style.cursor = "pointer";
            CrisisMoreSevere.style.cursor = "pointer";
            blocked_vehicle.style.cursor = "pointer";
            RAS.style.cursor = "pointer";
            CrisisLessSevere.style.cursor = "pointer";
            Etape3.style.visibility = 'hidden';
            Etape3_2.style.visibility = 'hidden';
            Icon5.style.visibility = 'hidden';
            Icon5_2.style.visibility = 'hidden';
            Icon6.style.visibility = 'hidden';
            Icon6_2.style.visibility = 'hidden';
            Instructions2.style.visibility = 'hidden';
            Instructions2_2.style.visibility = 'hidden';
            Instructions3.style.visibility = 'hidden';
            Instructions3_2.style.visibility = 'hidden';
            RoutePolice.style.visibility = 'hidden';
            RoutePompier.style.visibility = 'hidden';
            RoutePolice2.style.visibility = 'hidden';
            RoutePompier2.style.visibility = 'hidden';
            R1Police.style.visibility = 'hidden';
            R2Police.style.visibility = 'hidden';
            R3Police.style.visibility = 'hidden';
            R1Pompier.style.visibility = 'hidden';
            R2Pompier.style.visibility = 'hidden';
            R3Pompier.style.visibility = 'hidden';
            Etape4.style.visibility = "hidden";
            Icon7.style.visibility = "hidden";
            Icon8.style.visibility = "hidden";
            Instructions4.style.visibility = "hidden";
            Instructions4_2.style.visibility = "hidden";
            PVsent.style.visibility = "hidden";
            FTsent.style.visibility = "hidden";
            Etape5.style.visibility = "hidden";
            Icon9.style.visibility = "hidden";
            Icon10.style.visibility = "hidden";
            Instructions5.style.visibility = "hidden";
            Instructions5_2.style.visibility = "hidden";
            PVbreakdown.style.visibility = "hidden";
            FTbreakdown.style.visibility = "hidden";
            CrisisMoreSevere.style.visibility = "hidden";
            CrisisLessSevere.style.visibility = "hidden";
            Icon13.style.visibility = "hidden";
            Icon13_2.style.visibility = "hidden";
            Icon13_3.style.visibility = "hidden";
            Icon13_4.style.visibility = "hidden";
            blocked_vehicle.style.visibility = "hidden";
            RAS.style.visibility = "hidden";
        }
    }
    RAS.onclick = () => {
        CrisisMoreSevere.disabled = true;
        CrisisMoreSevere.style.backgroundColor = "lightgray";
        CrisisMoreSevere.style.top = "0px";
        CrisisMoreSevere.style.left = "0px";
        CrisisMoreSevere.style.boxShadow = "none";
        CrisisMoreSevere.style.cursor = "not-allowed";
        CrisisLessSevere.disabled = true;
        CrisisLessSevere.style.backgroundColor = "lightgray";
        CrisisLessSevere.style.top = "0px";
        CrisisLessSevere.style.left = "0px";
        CrisisLessSevere.style.boxShadow = "none";
        CrisisLessSevere.style.cursor = "not-allowed";
        blocked_vehicle.disabled = true;
        blocked_vehicle.style.backgroundColor = "lightgray";
        blocked_vehicle.style.top = "0px";
        blocked_vehicle.style.right = "0px";
        blocked_vehicle.style.boxShadow = "none";
        blocked_vehicle.style.cursor = "not-allowed";
        RAS.disabled = true;
        RAS.style.backgroundColor = "lightgray";
        RAS.style.top = "0px";
        RAS.style.right = "0px";
        RAS.style.boxShadow = "none";
        RAS.style.cursor = "not-allowed";
        check7();
    };
    CrisisLessSevere.onclick = () => {
        service.send(JSON.stringify("CrisisLessSevere"));
        CrisisMoreSevere.disabled = true;
        CrisisMoreSevere.style.backgroundColor = "lightgray";
        CrisisMoreSevere.style.top = "0px";
        CrisisMoreSevere.style.left = "0px";
        CrisisMoreSevere.style.boxShadow = "none";
        CrisisMoreSevere.style.cursor = "not-allowed";
        CrisisLessSevere.disabled = true;
        CrisisLessSevere.style.backgroundColor = "lightgray";
        CrisisLessSevere.style.top = "0px";
        CrisisLessSevere.style.left = "0px";
        CrisisLessSevere.style.boxShadow = "none";
        CrisisLessSevere.style.cursor = "not-allowed";
        blocked_vehicle.disabled = true;
        blocked_vehicle.style.backgroundColor = "lightgray";
        blocked_vehicle.style.top = "0px";
        blocked_vehicle.style.right = "0px";
        blocked_vehicle.style.boxShadow = "none";
        blocked_vehicle.style.cursor = "not-allowed";
        RAS.disabled = true;
        RAS.style.backgroundColor = "lightgray";
        RAS.style.top = "0px";
        RAS.style.right = "0px";
        RAS.style.boxShadow = "none";
        RAS.style.cursor = "not-allowed";
        check7();
    };
    function check7() {
        if (RAS.disabled == true && blocked_vehicle.disabled == true && CrisisLessSevere.disabled == true && CrisisMoreSevere.disabled == true) {
            Icon11.style.visibility = "visible";
            Icon12.style.visibility = "visible";
            PVArrived.style.visibility = "visible";
            FTArrived.style.visibility = "visible";
            Instructions5_3.style.visibility = "visible";
            Instructions5_4.style.visibility = "visible";
            etape5_3.style.visibility = "visible";
        }
    }
    /* - step 5_2 - */
    /* - arrived buttons - */
    PVArrived.onclick = () => {
        let nbVehiclesPolicier = document.getElementById("nbVehiclesPolicier").value;
        if (nbVehiclesPolicier == "1") {
            document.getElementById("PVvehiclesA1").innerHTML = "PV#1";
            document.getElementById("PVvehiclesA2").style.display = "none";
            document.getElementById("PVvehiclesA3").style.display = "none";
            document.getElementById("jump1_1A").style.display = "none";
            document.getElementById("jump2_1A").style.display = "none";
        }
        if (nbVehiclesPolicier == "2") {
            document.getElementById("PVvehiclesA1").innerHTML = "PV#1";
            document.getElementById("PVvehiclesA2").innerHTML = "PV#2";
            document.getElementById("PVvehiclesA3").style.display = "none";
            document.getElementById("jump2_1A").style.display = "none";
        }
        if (nbVehiclesPolicier == "3") {
            document.getElementById("PVvehiclesA1").innerHTML = "PV#1";
            document.getElementById("PVvehiclesA2").innerHTML = "PV#2";
            document.getElementById("PVvehiclesA3").innerHTML = "PV#3";
        }
        console.log("number of PV arrived : " + nbVehiclesPolicier);
        toggle11();
    };
    ConfirmArrivedPV.onclick = () => {
        PVArrived.style.cursor = 'not-allowed';
        PVArrived.style.backgroundColor = 'lightgray';
        PVArrived.style.top = '0px';
        PVArrived.style.left = '0px';
        PVArrived.disabled = true;
        PVArrived.style.boxShadow = "none";
        let nbVehiclesPolicier = document.getElementById("nbVehiclesPolicier").value;
        let nb = "PoliceVehicleArrived " + nbVehiclesPolicier;
        service.send(JSON.stringify(nb));
        toggle11();
        check6();
    };
    FTArrived.onclick = () => {
        let nbVehiclesPompier = document.getElementById("nbVehiclesPompier").value;
        if (nbVehiclesPompier == "1") {
            document.getElementById("FTvehiclesA1").innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2").style.display = "none";
            document.getElementById("FTvehiclesA3").style.display = "none";
            document.getElementById("FTvehiclesA4").style.display = "none";
            document.getElementById("jump1A").style.display = "none";
            document.getElementById("jump2A").style.display = "none";
            document.getElementById("jump3A").style.display = "none";
        }
        if (nbVehiclesPompier == "2") {
            document.getElementById("FTvehiclesA1").innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2").innerHTML = "FT#2";
            document.getElementById("FTvehiclesA3").style.display = "none";
            document.getElementById("FTvehiclesA4").style.display = "none";
            document.getElementById("jump2A").style.display = "none";
            document.getElementById("jump3A").style.display = "none";
        }
        if (nbVehiclesPompier == "3") {
            document.getElementById("FTvehiclesA1").innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2").innerHTML = "FT#2";
            document.getElementById("FTvehiclesA3").innerHTML = "FT#3";
            document.getElementById("FTvehiclesA4").style.display = "none";
            document.getElementById("jump3A").style.display = "none";
        }
        if (nbVehiclesPompier == "4") {
            document.getElementById("FTvehiclesA1").innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2").innerHTML = "FT#2";
            document.getElementById("FTvehiclesA3").innerHTML = "FT#3";
            document.getElementById("FTvehiclesA4").innerHTML = "FT#4";
        }
        console.log("number of FT arrived : " + nbVehiclesPompier);
        toggle12();
    };
    ConfirmArrivedFT.onclick = () => {
        FTArrived.style.cursor = 'not-allowed';
        FTArrived.style.backgroundColor = 'lightgray';
        FTArrived.style.top = '0px';
        FTArrived.style.right = '0px';
        FTArrived.disabled = true;
        FTArrived.style.boxShadow = "none";
        let nbVehiclesPompier = document.getElementById("nbVehiclesPompier").value;
        let nb = "FireTruckArrived " + nbVehiclesPompier;
        service.send(JSON.stringify(nb));
        toggle12();
        check6();
    };
    function check6() {
        if ((FTArrived.disabled == true) && (PVArrived.disabled == true)) {
            CloseButton.style.visibility = "visible";
            ResetButton.style.visibility = "visible";
            Etape6.style.visibility = "visible";
        }
    }
    /* - step 6 - */
    /* - close button - */
    CloseButton.onclick = () => {
        service.send(JSON.stringify("Close"));
        service.send(JSON.stringify("Stop"));
        window.close();
    };
    /* - reset button - */
    ResetButton.onclick = () => {
        service.send(JSON.stringify("Reset"));
        window.close();
    };
};
