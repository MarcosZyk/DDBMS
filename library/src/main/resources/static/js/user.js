$(document).ready(function () {
    let tags = '';
    getRequest(
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

}

function renderReadingHistory(readingInfos) {

}

function renderPossibleArticles(articles) {

}