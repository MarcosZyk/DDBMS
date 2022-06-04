let targetUid='';
$(document).ready(function () {
    let category = '';
    let tags = '';
    getRequest(
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

})

function renderDetail(detail) {

}

function renderComments(comments) {

}

function renderSimilarArticle(articles) {

}

function renderTargetUsers(users) {

}