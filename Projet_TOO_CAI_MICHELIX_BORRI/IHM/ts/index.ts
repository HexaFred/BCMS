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
    service.onclose = (event/*:CloseEvent*/) => {
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
        blur!.classList.toggle('active')
        var popup = document.getElementById('PoliceVehicleConfirm');
        popup!.classList.toggle('active')
    }

    function toggle2() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('PompierVehicleConfirm');
        popup!.classList.toggle('active')
    }

    /* - popup toggle routes PV - */
    function toggle3() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('RoutePoliceConfirm');
        popup!.classList.toggle('active')
    }

    function toggle4() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('RoutePompierConfirm');
        popup!.classList.toggle('active')
    }

    /* - popup toggle routes FT - */
    function toggle5() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('RoutePoliceConfirm2');
        popup!.classList.toggle('active')
    }

    function toggle6() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('RoutePompierConfirm2');
        popup!.classList.toggle('active')
    }

    /* - popup toggle dispatch - */
    function toggle7() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('DispatchPV');
        popup!.classList.toggle('active')
    }

    function toggle8() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('DispatchFT');
        popup!.classList.toggle('active')
    }

    /* - popup toggle breakdown - */
    function toggle9() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('breakdownPV');
        popup!.classList.toggle('active')
    }

    function toggle10() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('breakdownFT');
        popup!.classList.toggle('active')
    }

    /* - popup toggle blocked - */
    function toggle13() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('blockedVEHICLES');
        popup!.classList.toggle('active')
    }

    /* - popup toggle arrived - */
    function toggle11() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('arrivedPV');
        popup!.classList.toggle('active')
    }

    function toggle12() {
        var blur = document.getElementById('blur');
        blur!.classList.toggle('active')
        var popup = document.getElementById('arrivedFT');
        popup!.classList.toggle('active')
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
    let nbVehiclesPolicier = (<HTMLInputElement>document.getElementById("nbVehiclesPolicier"));
    let nbVehiclesPompier = (<HTMLInputElement>document.getElementById("nbVehiclesPompier"));

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
    let boolR1Policier: Boolean = false;
    let boolR2Policier: Boolean = false;
    let boolR3Policier: Boolean = false;
    let R1Pompier = document.getElementById("R1Pompier");
    let R2Pompier = document.getElementById("R2Pompier");
    let R3Pompier = document.getElementById("R3Pompier");
    let R1PompierCheck = document.getElementById("R1PompierCheck");
    let R2PompierCheck = document.getElementById("R2PompierCheck");
    let R3PompierCheck = document.getElementById("R3PompierCheck");
    let boolR1Pompier: Boolean = false;
    let boolR2Pompier: Boolean = false;
    let boolR3Pompier: Boolean = false;

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
    Crise!.onclick = () => {
        service.send(JSON.stringify("AjouterCrise"));
        (<HTMLInputElement>Crise).disabled = true;
        (<HTMLInputElement>Crise).style.top = '0px';
        (<HTMLInputElement>Crise).style.left = '0px';
        (<HTMLInputElement>Crise).style.boxShadow = 'none';
        (<HTMLInputElement>Crise).style.background = 'lightgray';
        (<HTMLInputElement>Crise).style.color = "black";
        (<HTMLInputElement>Crise).style.cursor = 'not-allowed';
        (<HTMLInputElement>connexionPolicier).disabled = false;
        (<HTMLInputElement>connexionPolicier).style.visibility = 'visible';
        (<HTMLInputElement>SeecheckPolice).style.visibility = 'visible';
        (<HTMLInputElement>connexionPompier).disabled = false;
        (<HTMLInputElement>connexionPompier).style.visibility = 'visible';
        (<HTMLInputElement>SeecheckPompier).style.visibility = 'visible';
        (<HTMLInputElement>Etape1).style.visibility = 'visible';
        console.log("Crisis started !");
    }

    /* - step 1 - */
    /* - connections buttons - */
    connexionPolicier!.onclick = () => {
        service.send(JSON.stringify("Policier"));
        (<HTMLInputElement>connexionPolicier).style.cursor = 'not-allowed';
        (<HTMLInputElement>connexionPolicier).style.backgroundColor = 'gray';
        (<HTMLInputElement>connexionPolicier).style.top = '0px';
        (<HTMLInputElement>connexionPolicier).style.left = '0px';
        (<HTMLInputElement>connexionPolicier).disabled = true;
        (<HTMLInputElement>checkPolice).checked = true;
        check();
        console.log("Police officer connected !");
    }

    connexionPompier!.onclick = () => {
        service.send(JSON.stringify("Pompier"));
        (<HTMLInputElement>connexionPompier).style.cursor = 'not-allowed';
        (<HTMLInputElement>connexionPompier).style.backgroundColor = 'gray';
        (<HTMLInputElement>connexionPompier).style.top = '0px';
        (<HTMLInputElement>connexionPompier).style.right = '0px';
        (<HTMLInputElement>connexionPompier).disabled = true;
        (<HTMLInputElement>checkPompier).checked = true;
        check();
        console.log("Firefighter connected !");
    }

    /* - check connection function to unlock Step 2 */
    function check() {
        if (((<HTMLInputElement>connexionPolicier).disabled == true) && ((<HTMLInputElement>connexionPompier).disabled == true)) {
            (<HTMLInputElement>Etape2).style.visibility = 'visible';
            (<HTMLInputElement>Icon3).style.visibility = 'visible';
            (<HTMLInputElement>Icon4).style.visibility = 'visible';
            (<HTMLInputElement>PolicierVehicles).style.visibility = 'visible';
            (<HTMLInputElement>PompierVehicles).style.visibility = 'visible';
            (<HTMLInputElement>nbVehiclesPolicier).style.visibility = 'visible';
            (<HTMLInputElement>nbVehiclesPompier).style.visibility = 'visible';
            (<HTMLInputElement>Instructions1).style.visibility = 'visible';
            (<HTMLInputElement>Instructions1_1).style.visibility = 'visible';
        }
    }

    /* - step 2 - */
    /* - vehicles buttons - */
    PolicierVehicles!.onclick = () => {
        let nbVehiclesPolicier = (<HTMLInputElement>document.getElementById("nbVehiclesPolicier")).value;
        if ((parseInt(nbVehiclesPolicier) < 1) || (parseInt(nbVehiclesPolicier) > 3) || nbVehiclesPolicier == '') {
            console.log("Error, you need to put a value between 1 to 3")
        }
        else {
            toggle();
        }
    }

    /* valeur nb  vehicles*/
    PompierVehicles!.onclick = () => {
        let nbVehiclesPompier = (<HTMLInputElement>document.getElementById("nbVehiclesPompier")).value;
        if ((parseInt(nbVehiclesPompier) < 1) || (parseInt(nbVehiclesPompier) > 4) || (nbVehiclesPompier == '')) {
            console.log("Error, you need to put a value between 1 to 4")
        }
        else {
            toggle2();
        }
    }

    /* - confirm vehicles buttons - */
    ConfirmPolice!.onclick = () => {
        let nbVehiclesPolicier = (<HTMLInputElement>document.getElementById("nbVehiclesPolicier")).value;
        let nb = "PolicierVehicles " + nbVehiclesPolicier;
        service.send(JSON.stringify(nb));
        (<HTMLInputElement>PolicierVehicles).disabled = true;
        (<HTMLInputElement>PolicierVehicles).style.top = '0px';
        (<HTMLInputElement>PolicierVehicles).style.left = '0px';
        (<HTMLInputElement>PolicierVehicles).style.cursor = 'not-allowed';
        (<HTMLInputElement>PolicierVehicles).style.color = 'gray';
        (<HTMLInputElement>PolicierVehicles).style.color = 'black';
        (<HTMLInputElement>PolicierVehicles).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>PolicierVehicles).style.boxShadow = 'none';
        toggle();
        check2();
        console.log("Police Vehicles Confirmed !")
    }

    ConfirmPompier!.onclick = () => {
        let nbVehiclesPompier = (<HTMLInputElement>document.getElementById("nbVehiclesPompier")).value;
        let nb = "PompierVehicles " + nbVehiclesPompier;
        service.send(JSON.stringify(nb));
        (<HTMLInputElement>PompierVehicles).disabled = true;
        (<HTMLInputElement>PompierVehicles).style.top = '0px';
        (<HTMLInputElement>PompierVehicles).style.right = '0px';
        (<HTMLInputElement>PompierVehicles).style.cursor = 'not-allowed';
        (<HTMLInputElement>PompierVehicles).style.color = 'black';
        (<HTMLInputElement>PompierVehicles).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>PompierVehicles).style.boxShadow = 'none';
        toggle2();
        check2();
        console.log("Fire Trucks Confirmed !")
    }

    /* - cancel vehicles buttons - */
    Cancel!.onclick = () => {
        toggle();
    }

    Cancel2!.onclick = () => {
        toggle2();
    }

    /* - check connection function to unlock Step 3 */
    function check2() {
        if (((<HTMLInputElement>PolicierVehicles).disabled == true) && ((<HTMLInputElement>PompierVehicles).disabled == true)) {
            (<HTMLInputElement>Etape3).style.visibility = 'visible';
            (<HTMLInputElement>Etape3_2).style.visibility = 'visible';
            (<HTMLInputElement>Icon5).style.visibility = 'visible';
            (<HTMLInputElement>Icon5_2).style.visibility = 'visible';
            (<HTMLInputElement>Icon6).style.visibility = 'visible';
            (<HTMLInputElement>Icon6_2).style.visibility = 'visible';
            (<HTMLInputElement>Instructions2).style.visibility = 'visible';
            (<HTMLInputElement>Instructions2_2).style.visibility = 'visible';
            (<HTMLInputElement>Instructions3).style.visibility = 'visible';
            (<HTMLInputElement>Instructions3_2).style.visibility = 'visible';
            (<HTMLInputElement>RoutePolice).style.visibility = 'visible';
            (<HTMLInputElement>RoutePompier).style.visibility = 'visible';
            (<HTMLInputElement>RoutePolice2).style.visibility = 'visible';
            (<HTMLInputElement>RoutePompier2).style.visibility = 'visible';
            (<HTMLInputElement>R1Police).style.visibility = 'visible';
            (<HTMLInputElement>R2Police).style.visibility = 'visible';
            (<HTMLInputElement>R3Police).style.visibility = 'visible';
            (<HTMLInputElement>R1Pompier).style.visibility = 'visible';
            (<HTMLInputElement>R2Pompier).style.visibility = 'visible';
            (<HTMLInputElement>R3Pompier).style.visibility = 'visible';
        }
    }

    /* - step 3 */
    /* - routes buttons - */
    /* - PV choices - */
    R1Police!.onclick = () => {
        (<HTMLInputElement>R1Police).disabled = true;
        (<HTMLInputElement>R1Police).style.cursor = 'not-allowed';
        (<HTMLInputElement>R1Police).style.color = 'black';
        (<HTMLInputElement>R1Police).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>R1Police).style.top = '-10px';
        (<HTMLInputElement>R1Police).style.scale = '1.15';
        (<HTMLInputElement>R1PoliceCheck).checked = true;
        (<HTMLInputElement>RoutePolice).disabled = false;
        (<HTMLInputElement>RoutePolice).style.cursor = 'pointer';

        if (boolR3Policier == false) {
            (<HTMLInputElement>R3Police).disabled = false;
            (<HTMLInputElement>R3Police).style.cursor = 'pointer';
            (<HTMLInputElement>R3Police).style.color = 'white';
            (<HTMLInputElement>R3Police).style.backgroundColor = 'black';
            (<HTMLInputElement>R3Police).style.top = '0px';
            (<HTMLInputElement>R3Police).style.scale = '1';
            (<HTMLInputElement>R3PoliceCheck).checked = false;
        }

        if (boolR2Policier == false) {
            (<HTMLInputElement>R2Police).disabled = false;
            (<HTMLInputElement>R2Police).style.cursor = 'pointer';
            (<HTMLInputElement>R2Police).style.color = 'white';
            (<HTMLInputElement>R2Police).style.backgroundColor = 'black';
            (<HTMLInputElement>R2Police).style.top = '0px';
            (<HTMLInputElement>R2Police).style.scale = '1';
            (<HTMLInputElement>R2PoliceCheck).checked = false;
        }
    }

    R2Police!.onclick = () => {
        (<HTMLInputElement>R2Police).disabled = true;
        (<HTMLInputElement>R2Police).style.cursor = 'not-allowed';
        (<HTMLInputElement>R2Police).style.color = 'black';
        (<HTMLInputElement>R2Police).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>R2Police).style.top = '-10px';
        (<HTMLInputElement>R2Police).style.scale = '1.15';
        (<HTMLInputElement>R2PoliceCheck).checked = true;
        (<HTMLInputElement>RoutePolice).disabled = false;
        (<HTMLInputElement>RoutePolice).style.cursor = 'pointer';

        if (boolR3Policier == false) {
            (<HTMLInputElement>R3Police).disabled = false;
            (<HTMLInputElement>R3Police).style.cursor = 'pointer';
            (<HTMLInputElement>R3Police).style.color = 'white';
            (<HTMLInputElement>R3Police).style.backgroundColor = 'black';
            (<HTMLInputElement>R3Police).style.top = '0px';
            (<HTMLInputElement>R3Police).style.scale = '1';
            (<HTMLInputElement>R3PoliceCheck).checked = false;
        }

        if (boolR1Policier == false) {
            (<HTMLInputElement>R1Police).disabled = false;
            (<HTMLInputElement>R1Police).style.cursor = 'pointer';
            (<HTMLInputElement>R1Police).style.color = 'white';
            (<HTMLInputElement>R1Police).style.backgroundColor = 'black';
            (<HTMLInputElement>R1Police).style.top = '0px';
            (<HTMLInputElement>R1Police).style.scale = '1';
            (<HTMLInputElement>R1PoliceCheck).checked = false;
        }
    }

    R3Police!.onclick = () => {
        (<HTMLInputElement>R3Police).disabled = true;
        (<HTMLInputElement>R3Police).style.cursor = 'not-allowed';
        (<HTMLInputElement>R3Police).style.color = 'black';
        (<HTMLInputElement>R3Police).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>R3Police).style.top = '-10px';
        (<HTMLInputElement>R3Police).style.scale = '1.15';
        (<HTMLInputElement>R3PoliceCheck).checked = true;
        (<HTMLInputElement>RoutePolice).disabled = false;
        (<HTMLInputElement>RoutePolice).style.cursor = 'pointer';

        if (boolR2Policier == false) {
            (<HTMLInputElement>R2Police).disabled = false;
            (<HTMLInputElement>R2Police).style.cursor = 'pointer';
            (<HTMLInputElement>R2Police).style.color = 'white';
            (<HTMLInputElement>R2Police).style.backgroundColor = 'black';
            (<HTMLInputElement>R2Police).style.top = '0px';
            (<HTMLInputElement>R2Police).style.scale = '1';
            (<HTMLInputElement>R2PoliceCheck).checked = false;
        }

        if (boolR1Policier == false) {
            (<HTMLInputElement>R1Police).disabled = false;
            (<HTMLInputElement>R1Police).style.cursor = 'pointer';
            (<HTMLInputElement>R1Police).style.color = 'white';
            (<HTMLInputElement>R1Police).style.backgroundColor = 'black';
            (<HTMLInputElement>R1Police).style.top = '0px';
            (<HTMLInputElement>R1Police).style.scale = '1';
            (<HTMLInputElement>R1PoliceCheck).checked = false;
        }
    }

    /* - FT choices - */
    R1Pompier!.onclick = () => {
        (<HTMLInputElement>R1Pompier).disabled = true;
        (<HTMLInputElement>R1Pompier).style.cursor = 'not-allowed';
        (<HTMLInputElement>R1Pompier).style.color = 'black';
        (<HTMLInputElement>R1Pompier).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>R1Pompier).style.top = '-10px';
        (<HTMLInputElement>R1Pompier).style.scale = '1.15';
        (<HTMLInputElement>R1PompierCheck).checked = true;
        (<HTMLInputElement>RoutePolice2).disabled = false;
        (<HTMLInputElement>RoutePolice2).style.cursor = 'pointer';

        if (boolR2Pompier == false) {
            (<HTMLInputElement>R2Pompier).disabled = false;
            (<HTMLInputElement>R2Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R2Pompier).style.color = 'white';
            (<HTMLInputElement>R2Pompier).style.backgroundColor = 'black';
            (<HTMLInputElement>R2Pompier).style.top = '0px';
            (<HTMLInputElement>R2Pompier).style.scale = '1';
            (<HTMLInputElement>R2PompierCheck).checked = false;
        }

        if (boolR3Pompier == false) {
            (<HTMLInputElement>R3Pompier).disabled = false;
            (<HTMLInputElement>R3Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R3Pompier).style.color = 'white';
            (<HTMLInputElement>R3Pompier).style.backgroundColor = 'black';
            (<HTMLInputElement>R3Pompier).style.top = '0px';
            (<HTMLInputElement>R3Pompier).style.scale = '1';
            (<HTMLInputElement>R3PompierCheck).checked = false;
        }
    }

    R2Pompier!.onclick = () => {
        (<HTMLInputElement>R2Pompier).disabled = true;
        (<HTMLInputElement>R2Pompier).style.cursor = 'not-allowed';
        (<HTMLInputElement>R2Pompier).style.color = 'black';
        (<HTMLInputElement>R2Pompier).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>R2Pompier).style.top = '-10px';
        (<HTMLInputElement>R2Pompier).style.scale = '1.15';
        (<HTMLInputElement>R2PompierCheck).checked = true;
        (<HTMLInputElement>RoutePolice2).disabled = false;
        (<HTMLInputElement>RoutePolice2).style.cursor = 'pointer';

        if (boolR1Pompier == false) {
            (<HTMLInputElement>R1Pompier).disabled = false;
            (<HTMLInputElement>R1Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R1Pompier).style.color = 'white';
            (<HTMLInputElement>R1Pompier).style.backgroundColor = 'black';
            (<HTMLInputElement>R1Pompier).style.top = '0px';
            (<HTMLInputElement>R1Pompier).style.scale = '1';
            (<HTMLInputElement>R1PompierCheck).checked = false;
        }

        if (boolR3Pompier == false) {
            (<HTMLInputElement>R3Pompier).disabled = false;
            (<HTMLInputElement>R3Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R3Pompier).style.color = 'white';
            (<HTMLInputElement>R3Pompier).style.backgroundColor = 'black';
            (<HTMLInputElement>R3Pompier).style.top = '0px';
            (<HTMLInputElement>R3Pompier).style.scale = '1';
            (<HTMLInputElement>R3PompierCheck).checked = false;
        }
    }

    R3Pompier!.onclick = () => {
        (<HTMLInputElement>R3Pompier).disabled = true;
        (<HTMLInputElement>R3Pompier).style.cursor = 'not-allowed';
        (<HTMLInputElement>R3Pompier).style.color = 'black';
        (<HTMLInputElement>R3Pompier).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>R3Pompier).style.top = '-10px';
        (<HTMLInputElement>R3Pompier).style.scale = '1.15';
        (<HTMLInputElement>R3PompierCheck).checked = true;
        (<HTMLInputElement>RoutePolice2).disabled = false;
        (<HTMLInputElement>RoutePolice2).style.cursor = 'pointer';

        if (boolR2Pompier == false) {
            (<HTMLInputElement>R2Pompier).disabled = false;
            (<HTMLInputElement>R2Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R2Pompier).style.color = 'white';
            (<HTMLInputElement>R2Pompier).style.backgroundColor = 'black';
            (<HTMLInputElement>R2Pompier).style.top = '0px';
            (<HTMLInputElement>R2Pompier).style.scale = '1';
            (<HTMLInputElement>R2PompierCheck).checked = false;
        }

        if (boolR1Pompier == false) {
            (<HTMLInputElement>R1Pompier).disabled = false;
            (<HTMLInputElement>R1Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R1Pompier).style.color = 'white';
            (<HTMLInputElement>R1Pompier).style.backgroundColor = 'black';
            (<HTMLInputElement>R1Pompier).style.top = '0px';
            (<HTMLInputElement>R1Pompier).style.scale = '1';
            (<HTMLInputElement>R1PompierCheck).checked = false;
        }
    }

    /* - routes send PV - */
    RoutePolice!.onclick = () => {
        toggle3();
    }

    RoutePompier!.onclick = () => {
        toggle4();
    }

    /* - functions desactivate buttons Routes PV - */
    function desactiveR1Police() {
        (<HTMLInputElement>R1Police).disabled = true;
        (<HTMLInputElement>R1Police).style.cursor = 'not-allowed';
    }

    function desactiveR2Police() {
        (<HTMLInputElement>R2Police).disabled = true;
        (<HTMLInputElement>R2Police).style.cursor = 'not-allowed';
    }

    function desactiveR3Police() {
        (<HTMLInputElement>R3Police).disabled = true;
        (<HTMLInputElement>R3Police).style.cursor = 'not-allowed';
    }

    /* - confirm routes buttons - */
    ConfirmRoutePolice!.onclick = () => {
        if ((<HTMLInputElement>R1PoliceCheck).checked == true) {
            boolR1Policier = true;
            desactiveR1Police();
            desactiveR2Police();
            desactiveR3Police();
            service.send(JSON.stringify("Route1PV"));
        }

        if ((<HTMLInputElement>R2PoliceCheck).checked == true) {
            boolR2Policier = true;
            desactiveR1Police();
            desactiveR2Police();
            desactiveR3Police();
            service.send(JSON.stringify("Route2PV"));
        }

        if ((<HTMLInputElement>R3PoliceCheck).checked == true) {
            boolR3Policier = true;
            desactiveR1Police();
            desactiveR2Police();
            desactiveR3Police();
            service.send(JSON.stringify("Route3PV"));
        }

        (<HTMLInputElement>RoutePolice).disabled = true;
        (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
        (<HTMLInputElement>RoutePolice).style.color = 'black';
        (<HTMLInputElement>RoutePolice).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>RoutePompier).disabled = false;
        (<HTMLInputElement>RoutePompier).style.cursor = 'pointer';
        toggle3();
    }

    ConfirmRoutePompier!.onclick = () => {
        (<HTMLInputElement>ConfirmRoutePompier).disabled = true;
        (<HTMLInputElement>RoutePompier).disabled = true;
        (<HTMLInputElement>RoutePompier).style.top = '0px';
        (<HTMLInputElement>RoutePompier).style.right = '0px';
        (<HTMLInputElement>RoutePompier).style.cursor = 'not-allowed';
        (<HTMLInputElement>RoutePompier).style.color = 'black';
        (<HTMLInputElement>RoutePompier).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>RoutePompier).style.boxShadow = 'none';
        (<HTMLInputElement>RoutePolice).style.top = '0px';
        (<HTMLInputElement>RoutePolice).style.left = '0px';
        (<HTMLInputElement>RoutePolice).style.boxShadow = 'none';
        (<HTMLInputElement>R1Police).style.top = '0px';
        (<HTMLInputElement>R2Police).style.top = '0px';
        (<HTMLInputElement>R3Police).style.top = '0px';
        toggle4();
        check3();
        console.log("Firefighter agreed, route PV chosen.")

        if (((<HTMLInputElement>R1Police).style.backgroundColor == 'lightgray') && ((<HTMLInputElement>ConfirmRoutePompier).disabled == true)) {
            service.send(JSON.stringify("AgreeRoute1PV"));
        }

        if (((<HTMLInputElement>R2Police).style.backgroundColor == 'lightgray') && ((<HTMLInputElement>ConfirmRoutePompier).disabled == true)) {
            service.send(JSON.stringify("AgreeRoute2PV"));
        }

        if (((<HTMLInputElement>R3Police).style.backgroundColor == 'lightgray') && ((<HTMLInputElement>ConfirmRoutePompier).disabled == true)) {
            service.send(JSON.stringify("AgreeRoute3PV"));
        }

    }

    /* - cancel routes PV buttons - */
    Cancel3!.onclick = () => {
        toggle3();
    }

    Cancel4!.onclick = () => {
        /* - simple - */
        if (boolR1Policier == true && boolR2Policier == false && boolR3Policier == false) {
            (<HTMLInputElement>R1Police).style.top = '0px';
            (<HTMLInputElement>R1Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Police).style.scale = '1';
            (<HTMLInputElement>R2Police).disabled = false;
            (<HTMLInputElement>R2Police).style.cursor = 'pointer';
            (<HTMLInputElement>R3Police).disabled = false;
            (<HTMLInputElement>R3Police).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice).disabled = true;
            (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1PV"));
            console.log("Route 1 for PV refused..");
        }

        if (boolR2Policier == true && boolR1Policier == false && boolR3Policier == false) {
            (<HTMLInputElement>R2Police).style.top = '0px';
            (<HTMLInputElement>R2Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Police).style.scale = '1';
            (<HTMLInputElement>R1Police).disabled = false;
            (<HTMLInputElement>R1Police).style.cursor = 'pointer';
            (<HTMLInputElement>R3Police).disabled = false;
            (<HTMLInputElement>R3Police).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice).disabled = true;
            (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2PV"));
            console.log("Route 2 for PV refused..");
        }

        if (boolR3Policier == true && boolR2Policier == false && boolR1Policier == false) {
            (<HTMLInputElement>R3Police).style.top = '0px';
            (<HTMLInputElement>R3Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Police).style.scale = '1';
            (<HTMLInputElement>R2Police).disabled = false;
            (<HTMLInputElement>R2Police).style.cursor = 'pointer';
            (<HTMLInputElement>R1Police).disabled = false;
            (<HTMLInputElement>R1Police).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice).disabled = true;
            (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute3PV"));
            console.log("Route 3 for PV refused..");
        }

        /* - double - */
        if (boolR1Policier == true && boolR2Policier == true && boolR3Policier == false) {
            (<HTMLInputElement>R1Police).style.top = '0px';
            (<HTMLInputElement>R1Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Police).style.scale = '1';
            (<HTMLInputElement>R2Police).style.top = '0px';
            (<HTMLInputElement>R2Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Police).style.scale = '1';
            (<HTMLInputElement>R3Police).disabled = false;
            (<HTMLInputElement>R3Police).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice).disabled = true;
            (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_2PV"));
            console.log("Routes 1 and 2 for PV refused..");
        }

        if (boolR3Policier == true && boolR2Policier == true && boolR1Policier == false) {
            (<HTMLInputElement>R3Police).style.top = '0px';
            (<HTMLInputElement>R3Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Police).style.scale = '1';
            (<HTMLInputElement>R2Police).style.top = '0px';
            (<HTMLInputElement>R2Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Police).style.scale = '1';
            (<HTMLInputElement>R1Police).disabled = false;
            (<HTMLInputElement>R1Police).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice).disabled = true;
            (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2_3PV"));
            console.log("Routes 2 and 3 for PV refused..");
        }

        if (boolR3Policier == true && boolR1Policier == true && boolR2Policier == false) {
            (<HTMLInputElement>R3Police).style.top = '0px';
            (<HTMLInputElement>R3Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Police).style.scale = '1';
            (<HTMLInputElement>R1Police).style.top = '0px';
            (<HTMLInputElement>R1Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Police).style.scale = '1';
            (<HTMLInputElement>R2Police).disabled = false;
            (<HTMLInputElement>R2Police).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice).disabled = true;
            (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_3PV"));
            console.log("Routes 1 and 3 for PV refused..");
        }

        /* - triple - */
        if (boolR3Policier == true && boolR1Policier == true && boolR2Policier == true) {
            (<HTMLInputElement>R3Police).style.top = '0px';
            (<HTMLInputElement>R3Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Police).style.scale = '1';
            (<HTMLInputElement>R1Police).style.top = '0px';
            (<HTMLInputElement>R1Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Police).style.scale = '1';
            (<HTMLInputElement>R2Police).style.top = '0px';
            (<HTMLInputElement>R2Police).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Police).style.scale = '1';
            (<HTMLInputElement>RoutePolice).disabled = true;
            (<HTMLInputElement>RoutePolice).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice).style.backgroundColor = 'lightgray';
            (<HTMLInputElement>RoutePolice).style.top = '0px';
            (<HTMLInputElement>RoutePolice).style.left = '0px';
            (<HTMLInputElement>RoutePompier).style.top = '0px';
            (<HTMLInputElement>RoutePompier).style.right = '0px';
            (<HTMLInputElement>RoutePompier).style.backgroundColor = 'lightgray';
            (<HTMLInputElement>ConfirmRoutePompier).disabled = true;
            console.log("All routes refused for PV, no more routes left.");
            service.send(JSON.stringify("NoMoreRouteLeftPV"));
        }
        (<HTMLInputElement>RoutePompier).disabled = true;
        (<HTMLInputElement>RoutePompier).style.cursor = 'not-allowed';
        toggle4();
        check3();
    }

    /* - routes send FT - */
    RoutePolice2!.onclick = () => {
        toggle5();
    }

    RoutePompier2!.onclick = () => {
        toggle6();
    }

    /* - functions desactivate buttons Routes FT - */
    function desactiveR1Pompier() {
        (<HTMLInputElement>R1Pompier).disabled = true;
        (<HTMLInputElement>R1Pompier).style.cursor = 'not-allowed';
    }

    function desactiveR2Pompier() {
        (<HTMLInputElement>R2Pompier).disabled = true;
        (<HTMLInputElement>R2Pompier).style.cursor = 'not-allowed';
    }

    function desactiveR3Pompier() {
        (<HTMLInputElement>R3Pompier).disabled = true;
        (<HTMLInputElement>R3Pompier).style.cursor = 'not-allowed';
    }

    /* - confirm routes buttons - */
    ConfirmRoutePolice2!.onclick = () => {
        if ((<HTMLInputElement>R1PompierCheck).checked == true) {
            boolR1Pompier = true;
            desactiveR1Pompier();
            desactiveR2Pompier();
            desactiveR3Pompier();
            service.send(JSON.stringify("Route1FT"));
        }

        if ((<HTMLInputElement>R2PompierCheck).checked == true) {
            boolR2Pompier = true;
            desactiveR1Pompier();
            desactiveR2Pompier();
            desactiveR3Pompier();
            service.send(JSON.stringify("Route2FT"));
        }

        if ((<HTMLInputElement>R3PompierCheck).checked == true) {
            boolR3Pompier = true;
            desactiveR1Pompier();
            desactiveR2Pompier();
            desactiveR3Pompier();
            service.send(JSON.stringify("Route3FT"));
        }

        (<HTMLInputElement>RoutePolice2).disabled = true;
        (<HTMLInputElement>RoutePolice2).style.top = '0px';
        (<HTMLInputElement>RoutePolice2).style.left = '0px';
        (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
        (<HTMLInputElement>RoutePolice2).style.color = 'black';
        (<HTMLInputElement>RoutePolice2).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>RoutePompier2).disabled = false;
        (<HTMLInputElement>RoutePompier2).style.cursor = 'pointer';
        toggle5();
    }

    ConfirmRoutePompier2!.onclick = () => {
        (<HTMLInputElement>ConfirmRoutePompier2).disabled = true;
        (<HTMLInputElement>RoutePompier2).disabled = true;
        (<HTMLInputElement>RoutePompier2).style.top = '0px';
        (<HTMLInputElement>RoutePompier2).style.right = '0px';
        (<HTMLInputElement>RoutePompier2).style.cursor = 'not-allowed';
        (<HTMLInputElement>RoutePompier2).style.color = 'black';
        (<HTMLInputElement>RoutePompier2).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>RoutePompier2).style.boxShadow = 'none';
        (<HTMLInputElement>RoutePolice2).style.top = '0px';
        (<HTMLInputElement>RoutePolice2).style.left = '0px';
        (<HTMLInputElement>RoutePolice2).style.boxShadow = 'none';
        (<HTMLInputElement>R1Pompier).style.top = '0px';
        (<HTMLInputElement>R2Pompier).style.top = '0px';
        (<HTMLInputElement>R3Pompier).style.top = '0px';
        toggle6();
        check3();
        console.log("Firefighters agreed, route FT chosen.")

        if (((<HTMLInputElement>R1Pompier).style.backgroundColor == 'lightgray') && ((<HTMLInputElement>ConfirmRoutePompier2).disabled == true)) {
            service.send(JSON.stringify("AgreeRoute1FT"));
        }

        if (((<HTMLInputElement>R2Pompier).style.backgroundColor == 'lightgray') && ((<HTMLInputElement>ConfirmRoutePompier2).disabled == true)) {
            service.send(JSON.stringify("AgreeRoute2FT"));
        }

        if (((<HTMLInputElement>R3Pompier).style.backgroundColor == 'lightgray') && ((<HTMLInputElement>ConfirmRoutePompier2).disabled == true)) {
            service.send(JSON.stringify("AgreeRoute3FT"));
        }
    }

    /* - cancel routes FT buttons - */
    Cancel5!.onclick = () => {
        toggle5();
    }

    Cancel6!.onclick = () => {
        /* - simple - */
        if (boolR1Pompier == true && boolR2Pompier == false && boolR3Pompier == false) {
            (<HTMLInputElement>R1Pompier).style.top = '0px';
            (<HTMLInputElement>R1Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Pompier).style.scale = '1';
            (<HTMLInputElement>R2Pompier).disabled = false;
            (<HTMLInputElement>R2Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R3Pompier).disabled = false;
            (<HTMLInputElement>R3Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice2).disabled = true;
            (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1FT"));
            console.log("Route 1 for FT refused..");
        }

        if (boolR2Pompier == true && boolR1Pompier == false && boolR3Pompier == false) {
            (<HTMLInputElement>R2Pompier).style.top = '0px';
            (<HTMLInputElement>R2Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Pompier).style.scale = '1';
            (<HTMLInputElement>R1Pompier).disabled = false;
            (<HTMLInputElement>R1Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R3Pompier).disabled = false;
            (<HTMLInputElement>R3Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice2).disabled = true;
            (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2FT"));
            console.log("Route 2 for FT refused..");
        }

        if (boolR3Pompier == true && boolR2Pompier == false && boolR1Pompier == false) {
            (<HTMLInputElement>R3Pompier).style.top = '0px';
            (<HTMLInputElement>R3Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Pompier).style.scale = '1';
            (<HTMLInputElement>R2Pompier).disabled = false;
            (<HTMLInputElement>R2Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>R1Pompier).disabled = false;
            (<HTMLInputElement>R1Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice2).disabled = true;
            (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute3FT"));
            console.log("Route 3 for FT refused..");
        }

        /* - double - */
        if (boolR1Pompier == true && boolR2Pompier == true && boolR3Pompier == false) {
            (<HTMLInputElement>R1Pompier).style.top = '0px';
            (<HTMLInputElement>R1Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Pompier).style.scale = '1';
            (<HTMLInputElement>R2Pompier).style.top = '0px';
            (<HTMLInputElement>R2Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Pompier).style.scale = '1';
            (<HTMLInputElement>R3Pompier).disabled = false;
            (<HTMLInputElement>R3Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice2).disabled = true;
            (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_2FT"));
            console.log("Routes 1 and 2 for FT refused..");
        }

        if (boolR3Pompier == true && boolR2Pompier == true && boolR1Pompier == false) {
            (<HTMLInputElement>R3Pompier).style.top = '0px';
            (<HTMLInputElement>R3Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Pompier).style.scale = '1';
            (<HTMLInputElement>R2Pompier).style.top = '0px';
            (<HTMLInputElement>R2Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Pompier).style.scale = '1';
            (<HTMLInputElement>R1Pompier).disabled = false;
            (<HTMLInputElement>R1Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice2).disabled = true;
            (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute2_3FT"));
            console.log("Routes 2 and 3 for FT refused..");
        }

        if (boolR3Pompier == true && boolR1Pompier == true && boolR2Pompier == false) {
            (<HTMLInputElement>R3Pompier).style.top = '0px';
            (<HTMLInputElement>R3Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Pompier).style.scale = '1';
            (<HTMLInputElement>R1Pompier).style.top = '0px';
            (<HTMLInputElement>R1Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Pompier).style.scale = '1';
            (<HTMLInputElement>R2Pompier).disabled = false;
            (<HTMLInputElement>R2Pompier).style.cursor = 'pointer';
            (<HTMLInputElement>RoutePolice2).disabled = true;
            (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = '#1cb182';
            service.send(JSON.stringify("RefuseRoute1_3FT"));
            console.log("Routes 1 and 3 for FT refused..");
        }

        /* - triple - */
        if (boolR3Pompier == true && boolR1Pompier == true && boolR2Pompier == true) {
            (<HTMLInputElement>R3Pompier).style.top = '0px';
            (<HTMLInputElement>R3Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R3Pompier).style.scale = '1';
            (<HTMLInputElement>R1Pompier).style.top = '0px';
            (<HTMLInputElement>R1Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R1Pompier).style.scale = '1';
            (<HTMLInputElement>R2Pompier).style.top = '0px';
            (<HTMLInputElement>R2Pompier).style.backgroundColor = '#a50000';
            (<HTMLInputElement>R2Pompier).style.scale = '1';
            (<HTMLInputElement>RoutePolice2).disabled = true;
            (<HTMLInputElement>RoutePolice2).style.cursor = 'not-allowed';
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = 'lightgray';
            (<HTMLInputElement>RoutePolice2).style.top = '0px';
            (<HTMLInputElement>RoutePolice2).style.left = '0px';
            (<HTMLInputElement>RoutePompier2).style.top = '0px';
            (<HTMLInputElement>RoutePompier2).style.right = '0px';
            (<HTMLInputElement>RoutePompier2).style.backgroundColor = 'lightgray';
            (<HTMLInputElement>ConfirmRoutePompier2).disabled = true;
            console.log("All routes refused for FT, no more routes left.");
            service.send(JSON.stringify("NoMoreRouteLeftFT"));
        }
        (<HTMLInputElement>RoutePompier2).disabled = true;
        (<HTMLInputElement>RoutePompier2).style.cursor = 'not-allowed';
        toggle6();
        check3();
    }


    /* - check connection function to unlock Step 4 */
    function check3() {
        if (((<HTMLInputElement>ConfirmRoutePompier).disabled == true) && ((<HTMLInputElement>ConfirmRoutePompier2).disabled == true)) {
            (<HTMLInputElement>Etape4).style.visibility = "visible";
            (<HTMLInputElement>Icon7).style.visibility = "visible";
            (<HTMLInputElement>Icon8).style.visibility = "visible";
            (<HTMLInputElement>Instructions4).style.visibility = "visible";
            (<HTMLInputElement>Instructions4_2).style.visibility = "visible";
            (<HTMLInputElement>PVsent).style.visibility = "visible";
            (<HTMLInputElement>FTsent).style.visibility = "visible";
        }
    }



    /* - step 4 - */
    /* - dispatch buttons - */
    PVsent!.onclick = () => {
        let nbVehiclesPolicier = (<HTMLInputElement>document.getElementById("nbVehiclesPolicier")).value;
        if (nbVehiclesPolicier == "1") {
            document.getElementById("PVvehicles1")!.innerHTML = "PV#1";
            document.getElementById("PVvehicles2")!.style.display = "none";
            document.getElementById("PVvehicles3")!.style.display = "none";
            document.getElementById("jump1_1")!.style.display = "none";
            document.getElementById("jump2_1")!.style.display = "none";
        }

        if (nbVehiclesPolicier == "2") {
            document.getElementById("PVvehicles1")!.innerHTML = "PV#1";
            document.getElementById("PVvehicles2")!.innerHTML = "PV#2";
            document.getElementById("PVvehicles3")!.style.display = "none";
            document.getElementById("jump2_1")!.style.display = "none";
        }

        if (nbVehiclesPolicier == "3") {
            document.getElementById("PVvehicles1")!.innerHTML = "PV#1";
            document.getElementById("PVvehicles2")!.innerHTML = "PV#2";
            document.getElementById("PVvehicles3")!.innerHTML = "PV#3";
        }
        console.log("number of PV dispatched : " + nbVehiclesPolicier)
        toggle7();
    }

    ConfirmDispatchPV!.onclick = () => {
        (<HTMLInputElement>PVsent).style.cursor = 'not-allowed';
        (<HTMLInputElement>PVsent).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>PVsent).style.top = '0px';
        (<HTMLInputElement>PVsent).style.left = '0px';
        (<HTMLInputElement>PVsent).disabled = true;
        (<HTMLInputElement>PVsent).style.boxShadow = "none";
        service.send(JSON.stringify("AllVehiclesDispatchedPV"));
        toggle7();
        check4();
    }

    FTsent!.onclick = () => {
        let nbVehiclesPompier = (<HTMLInputElement>document.getElementById("nbVehiclesPompier")).value;
        if (nbVehiclesPompier == "1") {
            document.getElementById("FTvehicles1")!.innerHTML = "FT#1";
            document.getElementById("FTvehicles2")!.style.display = "none";
            document.getElementById("FTvehicles3")!.style.display = "none";
            document.getElementById("FTvehicles4")!.style.display = "none";
            document.getElementById("jump1")!.style.display = "none";
            document.getElementById("jump2")!.style.display = "none";
            document.getElementById("jump3")!.style.display = "none";
        }

        if (nbVehiclesPompier == "2") {
            document.getElementById("FTvehicles1")!.innerHTML = "FT#1";
            document.getElementById("FTvehicles2")!.innerHTML = "FT#2";
            document.getElementById("FTvehicles3")!.style.display = "none";
            document.getElementById("FTvehicles4")!.style.display = "none";
            document.getElementById("jump2")!.style.display = "none";
            document.getElementById("jump3")!.style.display = "none";
        }

        if (nbVehiclesPompier == "3") {
            document.getElementById("FTvehicles1")!.innerHTML = "FT#1";
            document.getElementById("FTvehicles2")!.innerHTML = "FT#2";
            document.getElementById("FTvehicles3")!.innerHTML = "FT#3";
            document.getElementById("FTvehicles4")!.style.display = "none";
            document.getElementById("jump3")!.style.display = "none";
        }

        if (nbVehiclesPompier == "4") {
            document.getElementById("FTvehicles1")!.innerHTML = "FT#1";
            document.getElementById("FTvehicles2")!.innerHTML = "FT#2";
            document.getElementById("FTvehicles3")!.innerHTML = "FT#3";
            document.getElementById("FTvehicles4")!.innerHTML = "FT#4";
        }
        console.log("number of FT dispatched : " + nbVehiclesPompier);
        toggle8();
    }

    ConfirmDispatchFT!.onclick = () => {
        (<HTMLInputElement>FTsent).style.cursor = 'not-allowed';
        (<HTMLInputElement>FTsent).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>FTsent).style.top = '0px';
        (<HTMLInputElement>FTsent).style.right = '0px';
        (<HTMLInputElement>FTsent).disabled = true;
        (<HTMLInputElement>FTsent).style.boxShadow = "none";
        service.send(JSON.stringify("AllVehiclesDispatchedFT"));
        toggle8();
        check4();
    }

    /* - check connection function to unlock Step 5_1 */
    function check4() {
        if (((<HTMLInputElement>PVsent).disabled == true) && ((<HTMLInputElement>FTsent).disabled == true)) {
            (<HTMLInputElement>Etape5).style.visibility = "visible";
            (<HTMLInputElement>Icon9).style.visibility = "visible";
            (<HTMLInputElement>Icon10).style.visibility = "visible";
            (<HTMLInputElement>Instructions5).style.visibility = "visible";
            (<HTMLInputElement>Instructions5_2).style.visibility = "visible";
            (<HTMLInputElement>PVbreakdown).style.visibility = "visible";
            (<HTMLInputElement>FTbreakdown).style.visibility = "visible";
        }
    }


    /* - step 5 - */
    /* - breakdown buttons - */
    PVbreakdown!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownPV).disabled = true;
        (<HTMLInputElement>ConfirmBreakdownPV).style.cursor = "not-allowed";
        (<HTMLInputElement>ConfirmBreakdownPV_).disabled = true;
        (<HTMLInputElement>ConfirmBreakdownPV_).style.cursor = "not-allowed";
        let nbVehiclesPolicier = (<HTMLInputElement>document.getElementById("nbVehiclesPolicier")).value;
        if (nbVehiclesPolicier == "1") {
            document.getElementById("PV2Police")!.style.display = "none";
            document.getElementById("PV3Police")!.style.display = "none";
        }

        if (nbVehiclesPolicier == "2") {
            document.getElementById("PV3Police")!.style.display = "none";
        }
        toggle9();
    }

    CancelSelectionPV!.onclick = () => {
        (<HTMLInputElement>PV1Police).disabled = false;
        (<HTMLInputElement>PV1Police).style.backgroundColor = "#000000c2";
        (<HTMLInputElement>PV1Police).style.color = "white";
        (<HTMLInputElement>PV1Police).style.scale = '1';
        (<HTMLInputElement>PV1Police).style.cursor = 'pointer';
        (<HTMLInputElement>PV2Police).disabled = false;
        (<HTMLInputElement>PV2Police).style.backgroundColor = "#000000c2";
        (<HTMLInputElement>PV2Police).style.color = "white";
        (<HTMLInputElement>PV2Police).style.scale = '1';
        (<HTMLInputElement>PV2Police).style.cursor = 'pointer';
        (<HTMLInputElement>PV3Police).disabled = false;
        (<HTMLInputElement>PV3Police).style.backgroundColor = "#000000c2";
        (<HTMLInputElement>PV3Police).style.color = "white";
        (<HTMLInputElement>PV3Police).style.scale = '1';
        (<HTMLInputElement>PV3Police).style.cursor = 'pointer';
    }

    PV1Police!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownPV).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownPV).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmBreakdownPV_).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownPV_).style.cursor = "pointer";
        (<HTMLInputElement>PV1Police).disabled = true;
        (<HTMLInputElement>PV1Police).style.backgroundColor = "lightgray";
        (<HTMLInputElement>PV1Police).style.color = "black";
        (<HTMLInputElement>PV1Police).style.scale = '1.15';
        (<HTMLInputElement>PV1Police).style.cursor = 'not-allowed';
    }

    PV2Police!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownPV).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownPV).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmBreakdownPV_).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownPV_).style.cursor = "pointer";
        (<HTMLInputElement>PV2Police).disabled = true;
        (<HTMLInputElement>PV2Police).style.backgroundColor = "lightgray";
        (<HTMLInputElement>PV2Police).style.color = "black";
        (<HTMLInputElement>PV2Police).style.scale = '1.15';
        (<HTMLInputElement>PV2Police).style.cursor = 'not-allowed';
    }

    PV3Police!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownPV).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownPV).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmBreakdownPV_).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownPV_).style.cursor = "pointer";
        (<HTMLInputElement>PV3Police).disabled = true;
        (<HTMLInputElement>PV3Police).style.backgroundColor = "lightgray";
        (<HTMLInputElement>PV3Police).style.color = "black";
        (<HTMLInputElement>PV3Police).style.scale = '1.15';
        (<HTMLInputElement>PV3Police).style.cursor = 'not-allowed';
    }

    Cancel7!.onclick = () => {
        (<HTMLInputElement>PVbreakdown).disabled = true;
        (<HTMLInputElement>PVbreakdown).style.backgroundColor = "lightgray";
        (<HTMLInputElement>PVbreakdown).style.top = "0px";
        (<HTMLInputElement>PVbreakdown).style.left = "0px";
        (<HTMLInputElement>PVbreakdown).style.boxShadow = "none";
        (<HTMLInputElement>PVbreakdown).style.cursor = "not-allowed";
        (<HTMLInputElement>PVbreakdown).disabled = true;
        toggle9();
        check5();
    }

    ConfirmBreakdownPV!.onclick = () => {
        if ((<HTMLInputElement>PV1Police).disabled == true) {
            let nb = "PoliceVehicleBreakdown " + "1";
            service.send(JSON.stringify(nb));
        }

        if ((<HTMLInputElement>PV2Police).disabled == true) {
            let nb = "PoliceVehicleBreakdown " + "2";
            service.send(JSON.stringify(nb));
        }

        if ((<HTMLInputElement>PV3Police).disabled == true) {
            let nb = "PoliceVehicleBreakdown " + "3";
            service.send(JSON.stringify(nb));
        }

        if (((<HTMLInputElement>PV3Police).disabled == true) || ((<HTMLInputElement>PV1Police).disabled == true) || ((<HTMLInputElement>PV2Police).disabled == true)) {
            (<HTMLInputElement>PVbreakdown).disabled = true;
            (<HTMLInputElement>PVbreakdown).style.backgroundColor = "lightgray";
            (<HTMLInputElement>PVbreakdown).style.top = "0px";
            (<HTMLInputElement>PVbreakdown).style.left = "0px";
            (<HTMLInputElement>PVbreakdown).style.boxShadow = "none";
            (<HTMLInputElement>PVbreakdown).style.cursor = "not-allowed";
            toggle9();
            check5();
        }
    }

    FTbreakdown!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownFT).disabled = true;
        (<HTMLInputElement>ConfirmBreakdownFT).style.cursor = "not-allowed";
        (<HTMLInputElement>ConfirmBreakdownFT_).disabled = true;
        (<HTMLInputElement>ConfirmBreakdownFT_).style.cursor = "not-allowed";
        let nbVehiclesPompier = (<HTMLInputElement>document.getElementById("nbVehiclesPompier")).value;
        if (nbVehiclesPompier == "1") {
            document.getElementById("FT2Pompier")!.style.display = "none";
            document.getElementById("FT3Pompier")!.style.display = "none";
            document.getElementById("FT4Pompier")!.style.display = "none";
        }

        if (nbVehiclesPompier == "2") {
            document.getElementById("FT3Pompier")!.style.display = "none";
            document.getElementById("FT4Pompier")!.style.display = "none";
        }

        if (nbVehiclesPompier == "3") {
            document.getElementById("FT4Pompier")!.style.display = "none";
        }
        toggle10();
    }

    CancelSelectionFT!.onclick = () => {
        (<HTMLInputElement>FT1Pompier).disabled = false;
        (<HTMLInputElement>FT1Pompier).style.backgroundColor = "#000000c2";
        (<HTMLInputElement>FT1Pompier).style.scale = '1';
        (<HTMLInputElement>FT1Pompier).style.color = "white";
        (<HTMLInputElement>FT1Pompier).style.cursor = "pointer";
        (<HTMLInputElement>FT2Pompier).disabled = false;
        (<HTMLInputElement>FT2Pompier).style.backgroundColor = "#000000c2";
        (<HTMLInputElement>FT2Pompier).style.scale = '1';
        (<HTMLInputElement>FT2Pompier).style.color = "white";
        (<HTMLInputElement>FT2Pompier).style.cursor = "pointer";
        (<HTMLInputElement>FT3Pompier).disabled = false;
        (<HTMLInputElement>FT3Pompier).style.backgroundColor = "#000000c2";
        (<HTMLInputElement>FT3Pompier).style.color = "white";
        (<HTMLInputElement>FT3Pompier).style.cursor = "pointer";
        (<HTMLInputElement>FT3Pompier).style.scale = '1';
        (<HTMLInputElement>FT4Pompier).disabled = false;
        (<HTMLInputElement>FT4Pompier).style.backgroundColor = "#000000c2";
        (<HTMLInputElement>FT4Pompier).style.color = "white";
        (<HTMLInputElement>FT4Pompier).style.cursor = "pointer";
        (<HTMLInputElement>FT4Pompier).style.scale = '1';
    }

    FT1Pompier!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownFT).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmBreakdownFT_).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT_).style.cursor = "pointer";
        (<HTMLInputElement>FT1Pompier).disabled = true;
        (<HTMLInputElement>FT1Pompier).style.backgroundColor = "lightgray";
        (<HTMLInputElement>FT1Pompier).style.color = "black";
        (<HTMLInputElement>FT1Pompier).style.scale = '1.15';
        (<HTMLInputElement>FT1Pompier).style.cursor = 'not-allowed';
    }

    FT2Pompier!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownFT).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmBreakdownFT_).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT_).style.cursor = "pointer";
        (<HTMLInputElement>FT2Pompier).disabled = true;
        (<HTMLInputElement>FT2Pompier).style.backgroundColor = "lightgray";
        (<HTMLInputElement>FT2Pompier).style.color = "black";
        (<HTMLInputElement>FT2Pompier).style.scale = '1.15';
        (<HTMLInputElement>FT2Pompier).style.cursor = 'not-allowed';
    }

    FT3Pompier!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownFT).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmBreakdownFT_).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT_).style.cursor = "pointer";
        (<HTMLInputElement>FT3Pompier).disabled = true;
        (<HTMLInputElement>FT3Pompier).style.backgroundColor = "lightgray";
        (<HTMLInputElement>FT3Pompier).style.color = "black";
        (<HTMLInputElement>FT3Pompier).style.scale = '1.15';
        (<HTMLInputElement>FT3Pompier).style.cursor = 'not-allowed';
    }

    FT4Pompier!.onclick = () => {
        (<HTMLInputElement>ConfirmBreakdownFT).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmBreakdownFT_).disabled = false;
        (<HTMLInputElement>ConfirmBreakdownFT_).style.cursor = "pointer";
        (<HTMLInputElement>FT4Pompier).disabled = true;
        (<HTMLInputElement>FT4Pompier).style.backgroundColor = "lightgray";
        (<HTMLInputElement>FT4Pompier).style.color = "black";
        (<HTMLInputElement>FT4Pompier).style.scale = '1.15';
        (<HTMLInputElement>FT4Pompier).style.cursor = 'not-allowed';
    }

    ConfirmBreakdownFT!.onclick = () => {
        if ((<HTMLInputElement>FT1Pompier).disabled == true) {
            let nb = "PompierVehicleBreakdown " + "1";
            service.send(JSON.stringify(nb));
        }

        if ((<HTMLInputElement>FT2Pompier).disabled == true) {
            let nb = "PompierVehicleBreakdown " + "2";
            service.send(JSON.stringify(nb));
        }

        if ((<HTMLInputElement>FT3Pompier).disabled == true) {
            let nb = "PompierVehicleBreakdown " + "3";
            service.send(JSON.stringify(nb));
        }

        if ((<HTMLInputElement>FT4Pompier).disabled == true) {
            let nb = "PompierVehicleBreakdown " + "4";
            service.send(JSON.stringify(nb));
        }

        if (((<HTMLInputElement>FT1Pompier).disabled == true) || ((<HTMLInputElement>FT2Pompier).disabled == true) || ((<HTMLInputElement>FT3Pompier).disabled == true) || ((<HTMLInputElement>FT4Pompier).disabled == true)) {
            (<HTMLInputElement>FTbreakdown).disabled = true;
            (<HTMLInputElement>FTbreakdown).style.backgroundColor = "lightgray";
            (<HTMLInputElement>FTbreakdown).style.top = "0px";
            (<HTMLInputElement>FTbreakdown).style.right = "0px";
            (<HTMLInputElement>FTbreakdown).style.boxShadow = "none";
            (<HTMLInputElement>FTbreakdown).style.cursor = "not-allowed";
            toggle10();
            check5();
        }
    }

    Cancel8!.onclick = () => {
        (<HTMLInputElement>FTbreakdown).disabled = true;
        (<HTMLInputElement>FTbreakdown).style.backgroundColor = "lightgray";
        (<HTMLInputElement>FTbreakdown).style.top = "0px";
        (<HTMLInputElement>FTbreakdown).style.right = "0px";
        (<HTMLInputElement>FTbreakdown).style.boxShadow = "none";
        (<HTMLInputElement>FTbreakdown).style.cursor = "not-allowed";
        toggle10();
        check5();
    }

    /* - check connection function to unlock Step 5_2 */
    function check5() {
        if (((<HTMLInputElement>PVbreakdown).style.backgroundColor == "lightgray") && ((<HTMLInputElement>FTbreakdown).style.backgroundColor == "lightgray")) {
            (<HTMLInputElement>CrisisMoreSevere).style.visibility = "visible";
            (<HTMLInputElement>CrisisLessSevere).style.visibility = "visible";
            (<HTMLInputElement>Icon13).style.visibility = "visible";
            (<HTMLInputElement>Icon13_2).style.visibility = "visible";
            (<HTMLInputElement>Icon13_3).style.visibility = "visible";
            (<HTMLInputElement>Icon13_4).style.visibility = "visible";
            (<HTMLInputElement>blocked_vehicle).style.visibility = "visible";
            (<HTMLInputElement>RAS).style.visibility = "visible";
        }
    }




    /* - step 5_2 - */
    /* - blocked, crisis state buttons - */
    CrisisMoreSevere!.onclick = () => {
        service.send(JSON.stringify("CrisisMoreSevere"));
        (<HTMLInputElement>PolicierVehicles).disabled = false;
        (<HTMLInputElement>PompierVehicles).disabled = false;
        (<HTMLInputElement>RoutePolice).disabled = false;
        (<HTMLInputElement>RoutePolice2).disabled = false;
        (<HTMLInputElement>RoutePompier).disabled = false;
        (<HTMLInputElement>RoutePompier2).disabled = false;
        (<HTMLInputElement>R1Police).disabled = false;
        (<HTMLInputElement>R2Police).disabled = false;
        (<HTMLInputElement>R3Police).disabled = false;
        (<HTMLInputElement>R1Pompier).disabled = false;
        (<HTMLInputElement>R2Pompier).disabled = false;
        (<HTMLInputElement>R3Pompier).disabled = false;
        (<HTMLInputElement>ConfirmRoutePompier).disabled = false;
        (<HTMLInputElement>ConfirmRoutePompier2).disabled = false;
        (<HTMLInputElement>ConfirmRoutePolice).disabled = false;
        (<HTMLInputElement>PVsent).disabled = false;
        (<HTMLInputElement>FTsent).disabled = false;
        (<HTMLInputElement>PVbreakdown).disabled = false;
        (<HTMLInputElement>FTbreakdown).disabled = false;
        (<HTMLInputElement>CrisisMoreSevere).disabled = false;
        (<HTMLInputElement>blocked_vehicle).disabled = false;
        (<HTMLInputElement>RAS).disabled = false;
        (<HTMLInputElement>CrisisLessSevere).disabled = false;

        (<HTMLInputElement>PolicierVehicles).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>PompierVehicles).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>RoutePolice).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>RoutePolice2).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>RoutePompier).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>RoutePompier2).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>R1Police).style.backgroundColor = "black";
        (<HTMLInputElement>R2Police).style.backgroundColor = "black";
        (<HTMLInputElement>R3Police).style.backgroundColor = "black";
        (<HTMLInputElement>R1Pompier).style.backgroundColor = "black";
        (<HTMLInputElement>R2Pompier).style.backgroundColor = "black";
        (<HTMLInputElement>R3Pompier).style.backgroundColor = "black";
        (<HTMLInputElement>ConfirmRoutePompier).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>ConfirmRoutePompier2).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>ConfirmRoutePolice).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>PVsent).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>FTsent).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>PVbreakdown).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>FTbreakdown).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>CrisisMoreSevere).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>blocked_vehicle).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>RAS).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>CrisisLessSevere).style.backgroundColor = "#1cb182";

        (<HTMLInputElement>PolicierVehicles).style.color = "black";
        (<HTMLInputElement>PompierVehicles).style.color = "black";
        (<HTMLInputElement>RoutePolice).style.color = "black";
        (<HTMLInputElement>RoutePolice2).style.color = "black";
        (<HTMLInputElement>RoutePompier).style.color = "black";
        (<HTMLInputElement>RoutePompier2).style.color = "black";
        (<HTMLInputElement>R1Police).style.color = "white";
        (<HTMLInputElement>R2Police).style.color = "white";
        (<HTMLInputElement>R3Police).style.color = "white";
        (<HTMLInputElement>R1Pompier).style.color = "white";
        (<HTMLInputElement>R2Pompier).style.color = "white";
        (<HTMLInputElement>R3Pompier).style.color = "white";
        (<HTMLInputElement>ConfirmRoutePompier).style.color = "black";
        (<HTMLInputElement>ConfirmRoutePompier2).style.color = "black";
        (<HTMLInputElement>ConfirmRoutePolice).style.color = "black";
        (<HTMLInputElement>PVsent).style.color = "black";
        (<HTMLInputElement>FTsent).style.color = "black";
        (<HTMLInputElement>PVbreakdown).style.color = "black";
        (<HTMLInputElement>FTbreakdown).style.color = "black";
        (<HTMLInputElement>CrisisMoreSevere).style.color = "black";
        (<HTMLInputElement>blocked_vehicle).style.color = "black";
        (<HTMLInputElement>RAS).style.color = "black";
        (<HTMLInputElement>CrisisLessSevere).style.color = "black";

        (<HTMLInputElement>PolicierVehicles).style.cursor = "pointer";
        (<HTMLInputElement>PompierVehicles).style.cursor = "pointer";
        (<HTMLInputElement>R1Police).style.cursor = "pointer";
        (<HTMLInputElement>R2Police).style.cursor = "pointer";
        (<HTMLInputElement>R3Police).style.cursor = "pointer";
        (<HTMLInputElement>R1Pompier).style.cursor = "pointer";
        (<HTMLInputElement>R2Pompier).style.cursor = "pointer";
        (<HTMLInputElement>R3Pompier).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmRoutePompier).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmRoutePompier2).style.cursor = "pointer";
        (<HTMLInputElement>ConfirmRoutePolice).style.cursor = "pointer";
        (<HTMLInputElement>PVsent).style.cursor = "pointer";
        (<HTMLInputElement>FTsent).style.cursor = "pointer";
        (<HTMLInputElement>PVbreakdown).style.cursor = "pointer";
        (<HTMLInputElement>FTbreakdown).style.cursor = "pointer";
        (<HTMLInputElement>CrisisMoreSevere).style.cursor = "pointer";
        (<HTMLInputElement>blocked_vehicle).style.cursor = "pointer";
        (<HTMLInputElement>RAS).style.cursor = "pointer";
        (<HTMLInputElement>CrisisLessSevere).style.cursor = "pointer";

        (<HTMLInputElement>Etape3).style.visibility = 'hidden';
        (<HTMLInputElement>Etape3_2).style.visibility = 'hidden';
        (<HTMLInputElement>Icon5).style.visibility = 'hidden';
        (<HTMLInputElement>Icon5_2).style.visibility = 'hidden';
        (<HTMLInputElement>Icon6).style.visibility = 'hidden';
        (<HTMLInputElement>Icon6_2).style.visibility = 'hidden';
        (<HTMLInputElement>Instructions2).style.visibility = 'hidden';
        (<HTMLInputElement>Instructions2_2).style.visibility = 'hidden';
        (<HTMLInputElement>Instructions3).style.visibility = 'hidden';
        (<HTMLInputElement>Instructions3_2).style.visibility = 'hidden';
        (<HTMLInputElement>RoutePolice).style.visibility = 'hidden';
        (<HTMLInputElement>RoutePompier).style.visibility = 'hidden';
        (<HTMLInputElement>RoutePolice2).style.visibility = 'hidden';
        (<HTMLInputElement>RoutePompier2).style.visibility = 'hidden';
        (<HTMLInputElement>R1Police).style.visibility = 'hidden';
        (<HTMLInputElement>R2Police).style.visibility = 'hidden';
        (<HTMLInputElement>R3Police).style.visibility = 'hidden';
        (<HTMLInputElement>R1Pompier).style.visibility = 'hidden';
        (<HTMLInputElement>R2Pompier).style.visibility = 'hidden';
        (<HTMLInputElement>R3Pompier).style.visibility = 'hidden';
        (<HTMLInputElement>Etape4).style.visibility = "hidden";
        (<HTMLInputElement>Icon7).style.visibility = "hidden";
        (<HTMLInputElement>Icon8).style.visibility = "hidden";
        (<HTMLInputElement>Instructions4).style.visibility = "hidden";
        (<HTMLInputElement>Instructions4_2).style.visibility = "hidden";
        (<HTMLInputElement>PVsent).style.visibility = "hidden";
        (<HTMLInputElement>FTsent).style.visibility = "hidden";
        (<HTMLInputElement>Etape5).style.visibility = "hidden";
        (<HTMLInputElement>Icon9).style.visibility = "hidden";
        (<HTMLInputElement>Icon10).style.visibility = "hidden";
        (<HTMLInputElement>Instructions5).style.visibility = "hidden";
        (<HTMLInputElement>Instructions5_2).style.visibility = "hidden";
        (<HTMLInputElement>PVbreakdown).style.visibility = "hidden";
        (<HTMLInputElement>FTbreakdown).style.visibility = "hidden";
        (<HTMLInputElement>CrisisMoreSevere).style.visibility = "hidden";
        (<HTMLInputElement>CrisisLessSevere).style.visibility = "hidden";
        (<HTMLInputElement>Icon13).style.visibility = "hidden";
        (<HTMLInputElement>Icon13_2).style.visibility = "hidden";
        (<HTMLInputElement>Icon13_3).style.visibility = "hidden";
        (<HTMLInputElement>Icon13_4).style.visibility = "hidden";
        (<HTMLInputElement>blocked_vehicle).style.visibility = "hidden";
        (<HTMLInputElement>RAS).style.visibility = "hidden";
    }

    blocked_vehicle!.onclick = () => {
        (<HTMLInputElement>ConfirmBlockedPV).disabled = true;
        (<HTMLInputElement>ConfirmBlockedFT).disabled = true;
        (<HTMLInputElement>ConfirmBlockedPV).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>ConfirmBlockedFT).style.backgroundColor = "#1cb182";
        (<HTMLInputElement>PVPoliceBlocked).style.backgroundColor = "black";
        (<HTMLInputElement>PVPoliceBlocked).style.cursor = "cursor";
        (<HTMLInputElement>PVPoliceBlocked).disabled = false;
        (<HTMLInputElement>FTPompierBlocked).style.backgroundColor = "black";
        (<HTMLInputElement>FTPompierBlocked).style.cursor = "cursor";
        (<HTMLInputElement>FTPompierBlocked).disabled = false;
        toggle13();
    }

    PVPoliceBlocked!.onclick = () => {
        (<HTMLInputElement>PVPoliceBlocked).style.backgroundColor = "lightgray";
        (<HTMLInputElement>PVPoliceBlocked).style.cursor = "not-allowed";
        (<HTMLInputElement>PVPoliceBlocked).disabled = true;
        (<HTMLInputElement>ConfirmBlockedPV).disabled = false;
    }

    FTPompierBlocked!.onclick = () => {
        (<HTMLInputElement>FTPompierBlocked).style.backgroundColor = "lightgray";
        (<HTMLInputElement>FTPompierBlocked).style.cursor = "not-allowed";
        (<HTMLInputElement>FTPompierBlocked).disabled = true;
        (<HTMLInputElement>ConfirmBlockedPV).disabled = false;
    }

    ConfirmBlockedPV!.onclick = () => {
        (<HTMLInputElement>ConfirmBlockedPV).style.backgroundColor = "lightgray";
        (<HTMLInputElement>ConfirmBlockedPV).style.cursor = "not-allowed";
        (<HTMLInputElement>ConfirmBlockedPV).disabled = true;
        service.send(JSON.stringify("VehiculeBlockedPV"));
        check8();
    }

    ConfirmBlockedFT!.onclick = () => {
        (<HTMLInputElement>ConfirmBlockedFT).disabled = true;
        (<HTMLInputElement>ConfirmBlockedFT).style.backgroundColor = "lightgray";
        (<HTMLInputElement>ConfirmBlockedFT).style.cursor = "not-allowed";
        service.send(JSON.stringify("VehiculeBlockedFT"));
        check8();
    }

    function check8() {
        if (((<HTMLInputElement>ConfirmBlockedPV).style.backgroundColor == "lightgray") && ((<HTMLInputElement>ConfirmBlockedFT).style.backgroundColor == "lightgray")) {
            toggle13();
            (<HTMLInputElement>PolicierVehicles).disabled = false;
            (<HTMLInputElement>PompierVehicles).disabled = false;
            (<HTMLInputElement>RoutePolice).disabled = false;
            (<HTMLInputElement>RoutePolice2).disabled = false;
            (<HTMLInputElement>RoutePompier).disabled = false;
            (<HTMLInputElement>RoutePompier2).disabled = false;
            (<HTMLInputElement>R1Police).disabled = false;
            (<HTMLInputElement>R2Police).disabled = false;
            (<HTMLInputElement>R3Police).disabled = false;
            (<HTMLInputElement>R1Pompier).disabled = false;
            (<HTMLInputElement>R2Pompier).disabled = false;
            (<HTMLInputElement>R3Pompier).disabled = false;
            (<HTMLInputElement>ConfirmRoutePompier).disabled = false;
            (<HTMLInputElement>ConfirmRoutePompier2).disabled = false;
            (<HTMLInputElement>ConfirmRoutePolice).disabled = false;
            (<HTMLInputElement>PVsent).disabled = false;
            (<HTMLInputElement>FTsent).disabled = false;
            (<HTMLInputElement>PVbreakdown).disabled = false;
            (<HTMLInputElement>FTbreakdown).disabled = false;
            (<HTMLInputElement>CrisisMoreSevere).disabled = false;
            (<HTMLInputElement>blocked_vehicle).disabled = false;
            (<HTMLInputElement>RAS).disabled = false;
            (<HTMLInputElement>CrisisLessSevere).disabled = false;

            (<HTMLInputElement>PolicierVehicles).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>PompierVehicles).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>RoutePolice).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>RoutePolice2).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>RoutePompier).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>RoutePompier2).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>R1Police).style.backgroundColor = "black";
            (<HTMLInputElement>R2Police).style.backgroundColor = "black";
            (<HTMLInputElement>R3Police).style.backgroundColor = "black";
            (<HTMLInputElement>R1Pompier).style.backgroundColor = "black";
            (<HTMLInputElement>R2Pompier).style.backgroundColor = "black";
            (<HTMLInputElement>R3Pompier).style.backgroundColor = "black";
            (<HTMLInputElement>ConfirmRoutePompier).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>ConfirmRoutePompier2).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>ConfirmRoutePolice).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>PVsent).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>FTsent).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>PVbreakdown).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>FTbreakdown).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>CrisisMoreSevere).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>blocked_vehicle).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>RAS).style.backgroundColor = "#1cb182";
            (<HTMLInputElement>CrisisLessSevere).style.backgroundColor = "#1cb182";

            (<HTMLInputElement>PolicierVehicles).style.color = "black";
            (<HTMLInputElement>PompierVehicles).style.color = "black";
            (<HTMLInputElement>RoutePolice).style.color = "black";
            (<HTMLInputElement>RoutePolice2).style.color = "black";
            (<HTMLInputElement>RoutePompier).style.color = "black";
            (<HTMLInputElement>RoutePompier2).style.color = "black";
            (<HTMLInputElement>R1Police).style.color = "white";
            (<HTMLInputElement>R2Police).style.color = "white";
            (<HTMLInputElement>R3Police).style.color = "white";
            (<HTMLInputElement>R1Pompier).style.color = "white";
            (<HTMLInputElement>R2Pompier).style.color = "white";
            (<HTMLInputElement>R3Pompier).style.color = "white";
            (<HTMLInputElement>ConfirmRoutePompier).style.color = "black";
            (<HTMLInputElement>ConfirmRoutePompier2).style.color = "black";
            (<HTMLInputElement>ConfirmRoutePolice).style.color = "black";
            (<HTMLInputElement>PVsent).style.color = "black";
            (<HTMLInputElement>FTsent).style.color = "black";
            (<HTMLInputElement>PVbreakdown).style.color = "black";
            (<HTMLInputElement>FTbreakdown).style.color = "black";
            (<HTMLInputElement>CrisisMoreSevere).style.color = "black";
            (<HTMLInputElement>blocked_vehicle).style.color = "black";
            (<HTMLInputElement>RAS).style.color = "black";
            (<HTMLInputElement>CrisisLessSevere).style.color = "black";

            (<HTMLInputElement>PolicierVehicles).style.cursor = "pointer";
            (<HTMLInputElement>PompierVehicles).style.cursor = "pointer";
            (<HTMLInputElement>R1Police).style.cursor = "pointer";
            (<HTMLInputElement>R2Police).style.cursor = "pointer";
            (<HTMLInputElement>R3Police).style.cursor = "pointer";
            (<HTMLInputElement>R1Pompier).style.cursor = "pointer";
            (<HTMLInputElement>R2Pompier).style.cursor = "pointer";
            (<HTMLInputElement>R3Pompier).style.cursor = "pointer";
            (<HTMLInputElement>ConfirmRoutePompier).style.cursor = "pointer";
            (<HTMLInputElement>ConfirmRoutePompier2).style.cursor = "pointer";
            (<HTMLInputElement>ConfirmRoutePolice).style.cursor = "pointer";
            (<HTMLInputElement>PVsent).style.cursor = "pointer";
            (<HTMLInputElement>FTsent).style.cursor = "pointer";
            (<HTMLInputElement>PVbreakdown).style.cursor = "pointer";
            (<HTMLInputElement>FTbreakdown).style.cursor = "pointer";
            (<HTMLInputElement>CrisisMoreSevere).style.cursor = "pointer";
            (<HTMLInputElement>blocked_vehicle).style.cursor = "pointer";
            (<HTMLInputElement>RAS).style.cursor = "pointer";
            (<HTMLInputElement>CrisisLessSevere).style.cursor = "pointer";

            (<HTMLInputElement>Etape3).style.visibility = 'hidden';
            (<HTMLInputElement>Etape3_2).style.visibility = 'hidden';
            (<HTMLInputElement>Icon5).style.visibility = 'hidden';
            (<HTMLInputElement>Icon5_2).style.visibility = 'hidden';
            (<HTMLInputElement>Icon6).style.visibility = 'hidden';
            (<HTMLInputElement>Icon6_2).style.visibility = 'hidden';
            (<HTMLInputElement>Instructions2).style.visibility = 'hidden';
            (<HTMLInputElement>Instructions2_2).style.visibility = 'hidden';
            (<HTMLInputElement>Instructions3).style.visibility = 'hidden';
            (<HTMLInputElement>Instructions3_2).style.visibility = 'hidden';
            (<HTMLInputElement>RoutePolice).style.visibility = 'hidden';
            (<HTMLInputElement>RoutePompier).style.visibility = 'hidden';
            (<HTMLInputElement>RoutePolice2).style.visibility = 'hidden';
            (<HTMLInputElement>RoutePompier2).style.visibility = 'hidden';
            (<HTMLInputElement>R1Police).style.visibility = 'hidden';
            (<HTMLInputElement>R2Police).style.visibility = 'hidden';
            (<HTMLInputElement>R3Police).style.visibility = 'hidden';
            (<HTMLInputElement>R1Pompier).style.visibility = 'hidden';
            (<HTMLInputElement>R2Pompier).style.visibility = 'hidden';
            (<HTMLInputElement>R3Pompier).style.visibility = 'hidden';
            (<HTMLInputElement>Etape4).style.visibility = "hidden";
            (<HTMLInputElement>Icon7).style.visibility = "hidden";
            (<HTMLInputElement>Icon8).style.visibility = "hidden";
            (<HTMLInputElement>Instructions4).style.visibility = "hidden";
            (<HTMLInputElement>Instructions4_2).style.visibility = "hidden";
            (<HTMLInputElement>PVsent).style.visibility = "hidden";
            (<HTMLInputElement>FTsent).style.visibility = "hidden";
            (<HTMLInputElement>Etape5).style.visibility = "hidden";
            (<HTMLInputElement>Icon9).style.visibility = "hidden";
            (<HTMLInputElement>Icon10).style.visibility = "hidden";
            (<HTMLInputElement>Instructions5).style.visibility = "hidden";
            (<HTMLInputElement>Instructions5_2).style.visibility = "hidden";
            (<HTMLInputElement>PVbreakdown).style.visibility = "hidden";
            (<HTMLInputElement>FTbreakdown).style.visibility = "hidden";
            (<HTMLInputElement>CrisisMoreSevere).style.visibility = "hidden";
            (<HTMLInputElement>CrisisLessSevere).style.visibility = "hidden";
            (<HTMLInputElement>Icon13).style.visibility = "hidden";
            (<HTMLInputElement>Icon13_2).style.visibility = "hidden";
            (<HTMLInputElement>Icon13_3).style.visibility = "hidden";
            (<HTMLInputElement>Icon13_4).style.visibility = "hidden";
            (<HTMLInputElement>blocked_vehicle).style.visibility = "hidden";
            (<HTMLInputElement>RAS).style.visibility = "hidden";
        }
    }

    RAS!.onclick = () => {
        (<HTMLInputElement>CrisisMoreSevere).disabled = true;
        (<HTMLInputElement>CrisisMoreSevere).style.backgroundColor = "lightgray";
        (<HTMLInputElement>CrisisMoreSevere).style.top = "0px";
        (<HTMLInputElement>CrisisMoreSevere).style.left = "0px";
        (<HTMLInputElement>CrisisMoreSevere).style.boxShadow = "none";
        (<HTMLInputElement>CrisisMoreSevere).style.cursor = "not-allowed";

        (<HTMLInputElement>CrisisLessSevere).disabled = true;
        (<HTMLInputElement>CrisisLessSevere).style.backgroundColor = "lightgray";
        (<HTMLInputElement>CrisisLessSevere).style.top = "0px";
        (<HTMLInputElement>CrisisLessSevere).style.left = "0px";
        (<HTMLInputElement>CrisisLessSevere).style.boxShadow = "none";
        (<HTMLInputElement>CrisisLessSevere).style.cursor = "not-allowed";

        (<HTMLInputElement>blocked_vehicle).disabled = true;
        (<HTMLInputElement>blocked_vehicle).style.backgroundColor = "lightgray";
        (<HTMLInputElement>blocked_vehicle).style.top = "0px";
        (<HTMLInputElement>blocked_vehicle).style.right = "0px";
        (<HTMLInputElement>blocked_vehicle).style.boxShadow = "none";
        (<HTMLInputElement>blocked_vehicle).style.cursor = "not-allowed";

        (<HTMLInputElement>RAS).disabled = true;
        (<HTMLInputElement>RAS).style.backgroundColor = "lightgray";
        (<HTMLInputElement>RAS).style.top = "0px";
        (<HTMLInputElement>RAS).style.right = "0px";
        (<HTMLInputElement>RAS).style.boxShadow = "none";
        (<HTMLInputElement>RAS).style.cursor = "not-allowed";
        check7();
    }

    CrisisLessSevere!.onclick = () => {
        service.send(JSON.stringify("CrisisLessSevere"));
        (<HTMLInputElement>CrisisMoreSevere).disabled = true;
        (<HTMLInputElement>CrisisMoreSevere).style.backgroundColor = "lightgray";
        (<HTMLInputElement>CrisisMoreSevere).style.top = "0px";
        (<HTMLInputElement>CrisisMoreSevere).style.left = "0px";
        (<HTMLInputElement>CrisisMoreSevere).style.boxShadow = "none";
        (<HTMLInputElement>CrisisMoreSevere).style.cursor = "not-allowed";

        (<HTMLInputElement>CrisisLessSevere).disabled = true;
        (<HTMLInputElement>CrisisLessSevere).style.backgroundColor = "lightgray";
        (<HTMLInputElement>CrisisLessSevere).style.top = "0px";
        (<HTMLInputElement>CrisisLessSevere).style.left = "0px";
        (<HTMLInputElement>CrisisLessSevere).style.boxShadow = "none";
        (<HTMLInputElement>CrisisLessSevere).style.cursor = "not-allowed";

        (<HTMLInputElement>blocked_vehicle).disabled = true;
        (<HTMLInputElement>blocked_vehicle).style.backgroundColor = "lightgray";
        (<HTMLInputElement>blocked_vehicle).style.top = "0px";
        (<HTMLInputElement>blocked_vehicle).style.right = "0px";
        (<HTMLInputElement>blocked_vehicle).style.boxShadow = "none";
        (<HTMLInputElement>blocked_vehicle).style.cursor = "not-allowed";

        (<HTMLInputElement>RAS).disabled = true;
        (<HTMLInputElement>RAS).style.backgroundColor = "lightgray";
        (<HTMLInputElement>RAS).style.top = "0px";
        (<HTMLInputElement>RAS).style.right = "0px";
        (<HTMLInputElement>RAS).style.boxShadow = "none";
        (<HTMLInputElement>RAS).style.cursor = "not-allowed";
        check7();
    }

    function check7() {
        if ((<HTMLInputElement>RAS).disabled == true && (<HTMLInputElement>blocked_vehicle).disabled == true && (<HTMLInputElement>CrisisLessSevere).disabled == true && (<HTMLInputElement>CrisisMoreSevere).disabled == true) {
            (<HTMLInputElement>Icon11).style.visibility = "visible";
            (<HTMLInputElement>Icon12).style.visibility = "visible";
            (<HTMLInputElement>PVArrived).style.visibility = "visible";
            (<HTMLInputElement>FTArrived).style.visibility = "visible";
            (<HTMLInputElement>Instructions5_3).style.visibility = "visible";
            (<HTMLInputElement>Instructions5_4).style.visibility = "visible";
            (<HTMLInputElement>etape5_3).style.visibility = "visible";
        }
    }

    /* - step 5_2 - */
    /* - arrived buttons - */
    PVArrived!.onclick = () => {
        let nbVehiclesPolicier = (<HTMLInputElement>document.getElementById("nbVehiclesPolicier")).value;
        if (nbVehiclesPolicier == "1") {
            document.getElementById("PVvehiclesA1")!.innerHTML = "PV#1";
            document.getElementById("PVvehiclesA2")!.style.display = "none";
            document.getElementById("PVvehiclesA3")!.style.display = "none";
            document.getElementById("jump1_1A")!.style.display = "none";
            document.getElementById("jump2_1A")!.style.display = "none";
        }

        if (nbVehiclesPolicier == "2") {
            document.getElementById("PVvehiclesA1")!.innerHTML = "PV#1";
            document.getElementById("PVvehiclesA2")!.innerHTML = "PV#2";
            document.getElementById("PVvehiclesA3")!.style.display = "none";
            document.getElementById("jump2_1A")!.style.display = "none";
        }

        if (nbVehiclesPolicier == "3") {
            document.getElementById("PVvehiclesA1")!.innerHTML = "PV#1";
            document.getElementById("PVvehiclesA2")!.innerHTML = "PV#2";
            document.getElementById("PVvehiclesA3")!.innerHTML = "PV#3";
        }
        console.log("number of PV arrived : " + nbVehiclesPolicier)
        toggle11();
    }

    ConfirmArrivedPV!.onclick = () => {
        (<HTMLInputElement>PVArrived).style.cursor = 'not-allowed';
        (<HTMLInputElement>PVArrived).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>PVArrived).style.top = '0px';
        (<HTMLInputElement>PVArrived).style.left = '0px';
        (<HTMLInputElement>PVArrived).disabled = true;
        (<HTMLInputElement>PVArrived).style.boxShadow = "none";
        let nbVehiclesPolicier = (<HTMLInputElement>document.getElementById("nbVehiclesPolicier")).value;
        let nb = "PoliceVehicleArrived " + nbVehiclesPolicier;
        service.send(JSON.stringify(nb));
        toggle11();
        check6();
    }

    FTArrived!.onclick = () => {
        let nbVehiclesPompier = (<HTMLInputElement>document.getElementById("nbVehiclesPompier")).value;
        if (nbVehiclesPompier == "1") {
            document.getElementById("FTvehiclesA1")!.innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2")!.style.display = "none";
            document.getElementById("FTvehiclesA3")!.style.display = "none";
            document.getElementById("FTvehiclesA4")!.style.display = "none";
            document.getElementById("jump1A")!.style.display = "none";
            document.getElementById("jump2A")!.style.display = "none";
            document.getElementById("jump3A")!.style.display = "none";
        }
        if (nbVehiclesPompier == "2") {
            document.getElementById("FTvehiclesA1")!.innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2")!.innerHTML = "FT#2";
            document.getElementById("FTvehiclesA3")!.style.display = "none";
            document.getElementById("FTvehiclesA4")!.style.display = "none";
            document.getElementById("jump2A")!.style.display = "none";
            document.getElementById("jump3A")!.style.display = "none";
        }

        if (nbVehiclesPompier == "3") {
            document.getElementById("FTvehiclesA1")!.innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2")!.innerHTML = "FT#2";
            document.getElementById("FTvehiclesA3")!.innerHTML = "FT#3";
            document.getElementById("FTvehiclesA4")!.style.display = "none";
            document.getElementById("jump3A")!.style.display = "none";
        }

        if (nbVehiclesPompier == "4") {
            document.getElementById("FTvehiclesA1")!.innerHTML = "FT#1";
            document.getElementById("FTvehiclesA2")!.innerHTML = "FT#2";
            document.getElementById("FTvehiclesA3")!.innerHTML = "FT#3";
            document.getElementById("FTvehiclesA4")!.innerHTML = "FT#4";
        }
        console.log("number of FT arrived : " + nbVehiclesPompier);
        toggle12();
    }

    ConfirmArrivedFT!.onclick = () => {
        (<HTMLInputElement>FTArrived).style.cursor = 'not-allowed';
        (<HTMLInputElement>FTArrived).style.backgroundColor = 'lightgray';
        (<HTMLInputElement>FTArrived).style.top = '0px';
        (<HTMLInputElement>FTArrived).style.right = '0px';
        (<HTMLInputElement>FTArrived).disabled = true;
        (<HTMLInputElement>FTArrived).style.boxShadow = "none";
        let nbVehiclesPompier = (<HTMLInputElement>document.getElementById("nbVehiclesPompier")).value;
        let nb = "FireTruckArrived " + nbVehiclesPompier;
        service.send(JSON.stringify(nb));
        toggle12();
        check6();
    }

    function check6() {
        if (((<HTMLInputElement>FTArrived).disabled == true) && ((<HTMLInputElement>PVArrived).disabled == true)) {
            (<HTMLInputElement>CloseButton).style.visibility = "visible";
            (<HTMLInputElement>ResetButton).style.visibility = "visible";
            (<HTMLInputElement>Etape6).style.visibility = "visible";
        }
    }

    /* - step 6 - */
    /* - close button - */
    CloseButton!.onclick = () => {
        service.send(JSON.stringify("Close"));
        service.send(JSON.stringify("Stop"));
        window.close();
    }

    /* - reset button - */
    ResetButton!.onclick = () => {
        service.send(JSON.stringify("Reset"));
        window.close();
    }

}

