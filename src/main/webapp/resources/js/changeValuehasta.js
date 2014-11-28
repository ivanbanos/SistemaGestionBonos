/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function changeHasta(index) {
    var cantidad = $('#cantidad' + index + "");
    var hasta = $($('#hasta' + index).children()[0]);
    var desde = $('#desde' + index);
    var cantspan = $(cantidad.children()[0]);
    var cantinput = $(cantspan.children()[0]);

    var cantNumber = cantinput.val();
    var cantdesde = $(desde.children()[0]).text();

    if (cantNumber === '') {
        numbertoSum = 0;
    } else {
        var numbertoSum = parseInt(cantNumber);
    }

    var numberfrom = parseInt(cantdesde.substring(cantdesde.length - 4, cantdesde.length));
    var total = numberfrom + numbertoSum;
    var letra = cantdesde.substring(0, cantdesde.length - 4).trim();
    while (total >= 10000) {
        if (letra === 'A') {
            letra = 'B';
        } else
        if (letra === 'B') {
            letra = 'C';
        } else
        if (letra === 'C') {
            letra = 'D';
        } else
        if (letra === 'D') {
            letra = 'E';
        } else
        if (letra === 'E') {
            letra = 'F';
        } else
        if (letra === 'F') {
            letra = 'G';
        } else
        if (letra === 'G') {
            letra = 'H';
        } else
        if (letra === 'H') {
            letra = 'I';
        } else
        if (letra === 'I') {
            letra = 'J';
        } else
        if (letra === 'J') {
            letra = 'K';
        } else
        if (letra === 'K') {
            letra = 'L';
        } else
        if (letra === 'L') {
            letra = 'M';
        } else
        if (letra === 'M') {
            letra = 'N';
        } else
        if (letra === 'N') {
            letra = 'O';
        } else
        if (letra === 'O') {
            letra = 'P';
        } else
        if (letra === 'P') {
            letra = 'Q';
        } else
        if (letra === 'Q') {
            letra = 'R';
        } else
        if (letra === 'R') {
            letra = 'S';
        } else
        if (letra === 'S') {
            letra = 'T';
        } else
        if (letra === 'T') {
            letra = 'U';
        } else
        if (letra === 'U') {
            letra = 'V';
        } else
        if (letra === 'V') {
            letra = 'W';
        } else
        if (letra === 'W') {
            letra = 'X';
        } else
        if (letra === 'X') {
            letra = 'Y';
        } else
        if (letra === 'Y') {
            letra = 'Z';
        } else
        if (letra === 'Z') {
            letra = 'AA';
        }
        total = total - 10000;
    }
    if (total === 0) {
        hasta.text(letra + '000' + total);
    } else
    if (total < 100) {
        hasta.text(letra + '00' + total);
    } else if (total < 1000) {
        hasta.text(letra + '0' + total);
    } else {
        hasta.text(letra + '' + total);
    }
}