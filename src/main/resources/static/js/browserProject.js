function passHref(href) {
    let url= document.createElement('a');
    url.setAttribute('href', href);
    let projectId = url.search.substr(1);
    $.get({
        url: "/dashboardview/" + projectId

    });
};




