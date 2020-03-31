function passHref(href) {
    let url= document.createElement('a');
    url.setAttribute('href', href);
    let param = url.search.substr(1);
    $.get({
        url: "/dashboardview/" + param

    });
};



