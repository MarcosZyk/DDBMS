let targetAid='';

$(document).ready(function () {
    processDailyTop();
    processMonthlyTop();
    processYearlyTop();
});

function processDailyTop() {
    getRequest(
        '/dailyTop',
        function (res) {
            renderDailyTop(res);
        },
        function (error) {
            alert(error)
        }
    );

    function renderDailyTop(list) {
        renderTopList(list, $("#Daily-Top"));
    }
}

function processMonthlyTop() {
    getRequest(
        '/monthlyTop',
        function (res) {
            renderMonthlyTop(res);
        },
        function (error) {
            alert(error)
        }
    );

    function renderMonthlyTop(list) {
        renderTopList(list, $("#Monthly-Top"));
    }
}

function processYearlyTop() {
    getRequest(
        '/yearlyTop',
        function (res) {
            renderYearlyTop(res);
        },
        function (error) {
            alert(error)
        }
    );

    function renderYearlyTop(list) {
        renderTopList(list, $("#Yearly-Top"));
    }
}

function renderTopList(list, element) {
    let ui = '';
    list.forEach(
        function (article) {
            ui +=
                "<li class='list-group-item'>" +
                "<div class='article-view ' data-aid='" + article.aid + "' "+"onclick='directToArticlePage(this)'" +">" +
                article.title + "<br/>" +
                article.category + "<br/>" +
                article.tags + "<br/>" +
                "</div>" +
                "</li>";
        }
    );
    element.append(ui);
}


