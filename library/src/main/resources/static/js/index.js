$(document).ready(function () {
    processDailyTop();
    processWeeklyTop();
    processMonthlyTop();
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

function processWeeklyTop() {
    getRequest(
        '/weeklyTop',
        function (res) {
            renderWeeklyTop(res);
        },
        function (error) {
            alert(error)
        }
    );

    function renderWeeklyTop(list) {
        renderTopList(list, $("#Weekly-Top"));
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

function renderTopList(list, element) {
    let ui = '';
    list.forEach(
        function (article) {
            ui +=
                "<li class='list-group-item'>" +
                "<div class='article-view ' data-aid='" + article.aid + "' "+"onclick='directToArticlePage(this)'" +">" +
                "title: " + article.title + "<br/>" +
                "category: " + article.category + "<br/>" +
                "tags: " + article.tags + "<br/>" +
                "</div>" +
                "</li>";
        }
    );
    element.append(ui);
}


