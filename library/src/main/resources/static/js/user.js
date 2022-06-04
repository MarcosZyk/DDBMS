$(document).ready(function () {
    let  targetUid = window.location.href.split('?')[1].split('=')[1];
    let tags = '';
    getSyncRequest(
        '/getUser?uid=' + targetUid,
        function (res) {
            tags = res.preferTags;
            renderUser(res);
        },
        function (error) {
            alert(error);
        }
    );

    getRequest(
        '/getReadingInfos?uid=' + targetUid,
        function (res) {
            renderReadingHistory(res);
        },
        function (error) {
            alert(error);
        }
    );

    getRequest(
        '/getPossibleArticles?tags=' + tags,
        function (res) {
            renderPossibleArticles(res);
        },
        function (error) {
            alert(error);
        }
    );
})

function renderUser(user) {
    let element = $("#user-detail");

    let ui = '';
    for(let key in user){
        ui += key + ': '+user[key]+'<br/>'
    }

    element.append(ui);
}

function renderReadingHistory(readingInfos) {
    let element = $("#reading-infos");

    let ui = '<ul class="list-group">';
    readingInfos.forEach(function (detail) {
        ui += '<li class="card list-group-item"><div data-aid="'+detail.aid+'" onclick="directToArticlePage(this)">'
        for(let key in detail){
            ui += key + ': '+detail[key]+'<br/>';
        }
        ui += '</div></li>';
    })
    ui += '</ul>';

    element.append(ui);
}

function renderPossibleArticles(articles) {
    let element = $("#possible-articles");

    let ui = '<ul class="list-group">';
    articles.forEach(
        function (article) {
            ui +=
                "<li class='card list-group-item'>" +
                "<div data-aid='" + article.aid + "' "+"onclick='directToArticlePage(this)'" +">" +
                article.title + "<br/>" +
                article.category + "<br/>" +
                article.tags + "<br/>" +
                "</div>" +
                "</li>";
        }
    );
    ui += '</ul>';

    element.append(ui);
}