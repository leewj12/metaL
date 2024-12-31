document.addEventListener("DOMContentLoaded", function () {
    document.body.addEventListener('mouseover', function (event) {
        if (event.target.classList.contains('dropdown-toggle')) {
            const menu = event.target.nextElementSibling;
            menu.classList.add('show');
        } else if (event.target.classList.contains('dropdown-menu')) {
            event.target.classList.add('show');
        }
    });

    document.body.addEventListener('mouseout', function (event) {
        if (event.target.classList.contains('dropdown-toggle')) {
            const menu = event.target.nextElementSibling;
            if (!menu.contains(event.relatedTarget)) {
                menu.classList.remove('show');
            }
        } else if (event.target.classList.contains('dropdown-menu')) {
            if (!event.relatedTarget.classList.contains('dropdown-toggle')) {
                event.target.classList.remove('show');
            }
        }
    });
});