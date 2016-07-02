/*============================================
 Main menu
 ==============================================*/
(function(jQuery) {
    jQuery(document).ready(function() {
        var example = jQuery('#main-menu').superfish({
        });
    });
})(jQuery);

// mobile menu
jQuery(document).ready(function() {
    jQuery('#mobile-menu > span').click(function() {
        var mobile_menu = jQuery('#toggle-view-menu');
        if (mobile_menu.is(':hidden')) {
            mobile_menu.slideDown('300');
            jQuery(this).children('span').html('-');
        } else {
            mobile_menu.slideUp('300');
            jQuery(this).children('span').html('+');
        }
        jQuery(this).toggleClass('active');
    });
    jQuery('#toggle-view-menu li').click(function() {
        var text = jQuery(this).children('div.menu-panel');
        if (text.is(':hidden')) {
            text.slideDown('300');
            jQuery(this).children('span').html('-');
        } else {
            text.slideUp('300');
            jQuery(this).children('span').html('+');
        }
    });
});


/*============================================
 Validate form
 ==============================================*/


jQuery(document).ready(function() {
    if (jQuery("#kp-form-contact").length > 0) {
        jQuery('#kp-form-contact').validate({
            // Add requirements to each of the fields
            rules: {
                username: {
                    required: true,
                    maxlength: 15
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 6
                },
                repeatpassword: {
                	required: true,
                    minlength: 6
                }
            },
            // Specify what error messages to display
            // when the user does something horrid
            messages: {
                username: {
                    required: "Please enter your name.",
                    minlength: jQuery.format("Your username cannot be more than {0} characters.")
                },
                email: {
                    required: "Please enter your email.",
                    email: "Please enter a valid email."
                },
                password: {
                    required: "Please enter your password.",
                    password: "Please enter a valid password."
                },
                repeatpassword: {
                    required: "Please re-enter your password.",
                    minlength: jQuery.format("At least {0} characters required.")
                }
            },
            // Use Ajax to send everything to processForm.php
            submitHandler: function(form) {
                jQuery("#input-submit").attr("value", "Sending...");
                jQuery(form).ajaxSubmit({
                    success: function(responseText, statusText, xhr, $form) {
                        jQuery("#response").html(responseText).hide().slideDown("fast");
                        jQuery("#input-submit").attr("value", "Submit");
                    }
                });
                return false;
            }
        });
    }

    if (jQuery("#kp-form-comment").length > 0) {
        jQuery('#kp-form-comment').validate({
            // Add requirements to each of the fields
            rules: {
                name: {
                    required: true,
                    minlength: 2
                },
                email: {
                    required: true,
                    email: true
                },
                message: {
                    required: true,
                    minlength: 10
                }
            },
            // Specify what error messages to display
            // when the user does something horrid
            messages: {
                name: {
                    required: "Please enter your name.",
                    minlength: jQuery.format("At least {0} characters required.")
                },
                email: {
                    required: "Please enter your email.",
                    email: "Please enter a valid email."
                },
                url: {
                    required: "Please enter your url.",
                    url: "Please enter a valid url."
                },
                message: {
                    required: "Please enter a message.",
                    minlength: jQuery.format("At least {0} characters required.")
                }
            },
            // Use Ajax to send everything to processForm.php
            submitHandler: function(form) {
                jQuery("#submit-form").attr("value", "Sending...");
                jQuery(form).ajaxSubmit({
                    success: function(responseText, statusText, xhr, $form) {
                        jQuery("#response").html(responseText).hide().slideDown("fast");
                        jQuery("#submit-form").attr("value", "Submit");
                    }
                });
                return false;
            }
        });
    }
});

/*============================================
 tab
 ==============================================*/
jQuery('.kp-tabs a').click(function(e) {
    e.preventDefault();
    jQuery(this).tab('show');
})

jQuery(window).load(function() {
    var container = jQuery('.kp-tab-news .tab-content .tab-pane > ul');
    container.imagesLoaded(function() {
        container.masonry({
            itemSelector: '.post'
        });
    });
});
jQuery('.tab-kp-tab-news a').click(function(e) {
    e.preventDefault();
    jQuery(this).tab('show');
    var container = jQuery('.kp-tab-news .tab-content .tab-pane > ul');
    container.imagesLoaded(function() {
        container.masonry({
            itemSelector: '.post'
        });
    });
})





/*==========================================
 Carosel
 ============================================*/

jQuery(function() {
    if ($('#ca-main-news').length > 0) {
        jQuery('#ca-main-news').carouFredSel({
            responsive: true,
            width: '100%',
            prev: '#prev3',
            next: '#next3',
            auto: false,
            scroll: 1,
            items: {
                width: 600,
                height: '600',
                visible: {
                    min: 1,
                    max: 6
                }
            }
        });
    }
    ;

    // end ca-main-news
    if (jQuery('#ca-top-news').length > 0) {
        jQuery('#ca-top-news').carouFredSel({
            responsive: true,
            width: '100%',
            auto: true,
            delay: 3000,
            scroll: 1,
            items: {
                width: 600,
                height: 47,
                visible: {
                    min: 1,
                    max: 2
                }
            }
        });
    }
    ;

    // end ca-top-news
    if (jQuery('#ca-kp-story').length > 0) {
        jQuery('#ca-kp-story').carouFredSel({
            responsive: true,
            width: '100%',
            prev: '#prev1',
            next: '#next1',
            auto: false,
            pagination: "#pager1",
            scroll: 1,
            items: {
                width: 600,
                height: 'auto',
                visible: {
                    min: 1,
                    max: 6
                }
            }
        });
    }
    ;

    // end ca-kp-story
    if (jQuery('.slide-video').length > 0) {
        jQuery('.slide-video').carouFredSel({
            responsive: true,
            width: '100%',
            prev: '#prev4',
            next: '#next4',
            auto: false,
            scroll: 1,
            items: {
                width: 940,
                height: 'auto',
                visible: {
                    min: 1,
                    max: 6
                }
            }
        });
    }
    ;

    if (jQuery('.list-related-post').length > 0) {
        jQuery('.list-related-post').carouFredSel({
            width: '100%',
            prev: '#prev5',
            next: '#next5',
            pagination: "#pager5",
            align: "left",
            auto: false,
            scroll: 1
        });
    }
    ;


});



/*=========================================
 Flickr
 ===========================================*/

jQuery(document).ready(function() {
    if (jQuery('#flickr-feed-1 ul').length > 0) {
        jQuery('#flickr-feed-1 ul').jflickrfeed({
            limit: 6,
            qstrings: {
                id: '78715597@N07'
            },
            itemTemplate:
                    '<li class="flickr-badge-image">' +
                    '<a target="blank" href="{{link}}" title="{{title}}">' +
                    '<img src="{{image_s}}" alt="{{title}}" width="100px" height="100px" />' +
                    '</a>' +
                    '</li>'
        });
    }
    ;


});

/* =========================================================
 Tweets
 ============================================================ */
jQuery(document).ready(function() {
    if (jQuery('#tweets').length > 0) {
        jQuery('#tweets').tweetable({
            username: 'kopasoft ',
            time: true,
            rotate: false,
            speed: 4000,
            limit: 2,
            replies: false,
            position: 'append',
            failed: "Sorry, twitter is currently unavailable for this user.",
            loading: "Loading tweets...",
            html5: true,
            onComplete: function($ul) {
                jQuery('time').timeago();
            }
        });
    }
    ;
})

/* =========================================================
 Toggle Boxes
 ============================================================ */

jQuery(document).ready(function() {
    if (jQuery('.toggle-view-1 li').length > 0) {
        jQuery('.toggle-view-1 li').click(function() {
            if (jQuery(this).find('.panel').is(':hidden')) {
                jQuery(this).parent().find('li .panel').slideUp('slow');
                jQuery(this).parent().find('li').removeClass('active');
                jQuery(this).parent().find('li > span').html('+');
                jQuery(this).find('.panel').slideDown('slow');
                jQuery(this).addClass('active');
                jQuery(this).find('span').html('-');
            }
            else {
                jQuery(this).removeClass('active');
                jQuery(this).find('.panel').slideUp('slow');
                jQuery(this).find('span').html('+');
            }
            ;
        });
    }
    ;

});

jQuery(document).ready(function() {
    if (jQuery('.toggle-view-2 li').length > 0) {
        jQuery('.toggle-view-2 li').click(function() {
            if (jQuery(this).find('.panel').is(':hidden')) {
                jQuery(this).find('.panel').slideDown('slow');
                jQuery(this).addClass('active');
                jQuery(this).find('span').html('-');
            }
            else {
                jQuery(this).removeClass('active');
                jQuery(this).find('.panel').slideUp('slow');
                jQuery(this).find('span').html('+');
            }
            ;
        });
    }
    ;
});


jQuery(document).ready(function() {
    buttons = jQuery('.kp-show');
    if (buttons.length > 0) {
        jQuery.each(buttons, function(index, element) {
            jQuery(this).click(function() {
                items = jQuery(this).parent().find('.rating-hidden');
                if (items.is(':hidden'))
                    items.slideDown('slow');
                else
                    items.slideUp('slow');
            });
        });
    }
});


jQuery(document).ready(function() {
    jQuery('#sidebar > ul > li:nth-child(4)').addClass('num4');
    jQuery('#sidebar > ul > li:even').addClass('even');
});
/*====================
 rating
 ==================*/
jQuery(document).ready(function() {
    var send_rate = jQuery('.kp-rating .send-rate');
    if (send_rate.length > 0) {
        send_rate.click(function() {
            var answer = confirm("Do you want confirm?");
            if (answer === true) {
                jQuery('.slider').slider({disabled: true});
                send_rate.css('display', 'none');
            }
            return false;
        });
    }
    ;
});


jQuery(function() {
    $('.kp-rating .list-rating li').each(function(index) {

        $(this).find('.slider').addClass('slider' + index);
        $(this).find('.amount').addClass('amount' + index);

        $(".slider" + index).slider({
            value: 5,
            min: 0,
            max: 10,
            range: "min",
            step: 0.1,
            slide: function(event, ui) {
                jQuery(".amount" + index).val(ui.value);
                jQuery('.kp-rating .send-rate').css('display', 'block');
            }
        });

        jQuery(".amount" + index).val(jQuery(".slider" + index).slider("value"));

    });
});



jQuery(document).ready(function() {
    var rate = $('.kp-rating .list-rating li');
    if (rate.length > 0) {
        jQuery(rate).click(function() {
            jQuery(this).find('.sliderui').slideToggle();
        });
    }
    ;

});
/*====================
 slideshow
 ==================*/
jQuery(document).ready(function() {
    Grid.init();
});

/*
 	jQuery(document).ready(function() {
    	jQuery('<img src="images/icon/after.png"/>').appendTo('.kp-comment p');
	}) 
*/

jQuery(document).ready(function() {
    if (jQuery('#bottom-menu ul').length > 0) {
        jQuery('#bottom-menu li ul').remove();
    }
});

// load content
jQuery(document).ready(function(jQuery) {
    var widgets = jQuery('.kp-tab-news');
    if (widgets.length > 0) {
        $.each(widgets, function(index, item) {
            var obj = jQuery(this);
            jQuery(this).find('.tab-read-more').click(function(e) {
                e.preventDefault();
                url = obj.find('.nav-tabs li.active a').attr('href');
                jQuery(url).load("content/load-more.html");
            });

        });
    }
    ;
});
// masonry
jQuery(window).load(function() {

    var container = jQuery('#list-post-cat-2');
    container.imagesLoaded(function() {
        container.masonry({
            itemSelector: '.post'
        });
    });
});
jQuery(window).load(function() {

    var container = jQuery('.list-post-video');
    container.imagesLoaded(function() {
        container.masonry({
            itemSelector: '.video-post'
        });
    });
});

// prettyphoto
$(document).ready(function() {
    $("a[rel^='prettyPhoto']").prettyPhoto({animation_speed: 'fast', slideshow: 10000, hideflash: true});
});

function getthedate() {
	// clock
	var dayarray = new Array("Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi")
	var montharray = new Array("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")

	var mydate = new Date()
    var year = mydate.getYear()
    if (year < 1000)
        year += 1900
    var day = mydate.getDay()
    var month = mydate.getMonth()
    var daym = mydate.getDate()
    if (daym < 10)
        daym = "0" + daym
    var hours = mydate.getHours()
    var minutes = mydate.getMinutes()
    var seconds = mydate.getSeconds()
    /*var dn = "AM"
    if (hours >= 12)
        dn = "PM"
    if (hours > 12) {
        hours = hours - 12
    }
    if (hours == 0)
        hours = 12*/
    if (minutes <= 9)
        minutes = "0" + minutes
    if (seconds <= 9)
        seconds = "0" + seconds
//change font size here
    var cdate = "<span>" + dayarray[day] + " " + daym + "/" + montharray[month] + "/" + year + " " + hours + ":" + minutes + ":" + seconds
            + "</span>"
    if (document.all)
        document.all.clock.innerHTML = cdate
    else if (document.getElementById)
        document.getElementById("clock").innerHTML = cdate
    else
        document.write(cdate)
}
if (!document.all && !document.getElementById)
    getthedate()
function goforit() {
    if (document.all || document.getElementById)
        setInterval("getthedate()", 1000)
}


jQuery(window).load(function(){
  
  jQuery('.kp-gallery-carousel').flexslider({
    animation: "slide",
    controlNav: false,
    slideshow: false,
    itemWidth: 126,
    asNavFor: '.kp-gallery-slider'
  });
  
  jQuery('.kp-gallery-slider').flexslider({
    animation: "slide",
    controlNav: false,
    slideshow: false,
    sync: ".kp-gallery-carousel",
    start: function(slider){
      jQuery('body').removeClass('loading');
    }
  });
});