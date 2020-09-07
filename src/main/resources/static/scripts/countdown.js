var countdownDate = new Date([[${nextMarketDate}]]).getTime();
const countdownElement = document.getElementById("countdown");

const task = setInterval(function () {
    var now = new Date().getTime();

    var difference = countdownDate - now;

    var days = Math.floor(difference / (1000 * 60 * 60 * 24));
    var hours = Math.floor((difference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((difference % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((difference % (1000 * 60)) / 1000);

    var timeString = "";

    if (days > 0) {
        timeString += days + " dias, "
    } else if (hours > 0) {
        timeString += hours + " horas, "
    } else if (minutes > 0) {
        timeString += minutes + " minutos e "
    } else if (seconds > 0) {
        timeString += seconds + " segundos"
    }

    countdownElement.innerHTML = timeString

    if (difference < 0) {
        clearInterval(task);
        countdownElement.innerHTML = "Atualize a página para ver o novo melhor mercado!";
    }
}, 1000);