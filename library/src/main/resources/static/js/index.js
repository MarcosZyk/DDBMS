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
                "<li class='top-list-item'>" +
                "<div class='article-view' data-aid='" + article.id + "' "+"onclick='directToArticlePage(this)'" +">" +
                article.title +
                article.category +
                article.tags +
                "</div>" +
                "</li>";
        }
    );
    element.append(ui);
}

function directToArticlePage(element){
    aid = $(element).attr('data-aid');
    window.location.href = '/article';
}

