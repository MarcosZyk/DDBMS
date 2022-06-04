$(document).ready(function () {
    let targetAid = window.location.href.split('?')[1].split('=')[1];
    let category = '';
    let tags = '';
    getSyncRequest(
        'getArticle?aid=' + targetAid,
        function (res) {
            category = res.category;
            tags = res.tags;
            renderDetail(res);
        },
        function (error) {
            alert(error);
        }
    );

    getRequest(
        '/getComments?aid=' + targetAid,
        function (res) {
            renderComments(res);
        },
        function (error) {
            alert(error);
        }
    );

    getRequest(
        '/getSimilarArticles?category=' + category + '&tags=' + tags,
        function (res) {
            renderSimilarArticle(res);
        },
        function (error) {
            alert(error)
        }
    );

    getRequest(
        '/getPossibleUsers?tags=' + tags,
        function (res) {
            renderTargetUsers(res)
        },
        function (error) {
            alert(error);
        }
    );

});

function renderDetail(detail) {
    let element = $("#article-detail");

    let ui = '';
    for(let key in detail){
        ui += key + ': '+detail[key]+'<br/>'
    }

    element.append(ui);

    let videoName = detail.video;
    if (videoName !== '' && videoName != null) {
        element.append('<video controls src="/video/' + videoName + '"></video>')
    }

    let imageList = detail.image.split(',');
    let image = '';
    imageList.forEach(function (imageName) {
        image += '<img src="/picture/' + imageName + '" height="240px" alt="">';
    })
    element.append(image);

}

function renderComments(comments) {
    let element = $("#comments");

    let ui = '<ul class="list-group">';
    comments.forEach(function (detail) {
        ui += '<li class="card list-group-item"><div data-uid="'+detail.uid+'" onclick="directToUserPage(this)">'
        for(let key in detail){
            ui += key + ': '+detail[key]+'<br/>';
        }
        ui += '</div></li>';
    })
    ui += '</ul>';

    element.append(ui);
}

function renderSimilarArticle(articles) {
    let element = $("#similar-articles");

    let ui = '<ul class="list-group">';
    articles.forEach(
        function (article) {
            ui +=
                "<li  class='card list-group-item'>" +
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

function renderTargetUsers(users) {
    let element = $("#possible-users");

    let ui = '<ul class="list-group">';
    users.forEach(function (detail) {
        ui += '<li class="card list-group-item"><div data-uid="'+detail.uid+'" onclick="directToUserPage(this)">'
        for(let key in detail){
            ui += key + ': '+detail[key]+'<br/>';
        }
        ui += '</div></li>';
    })
    ui += '</ul>';

    element.append(ui);
}
