$(function () {
  // 메뉴
  $(".depth1 > li")
    .mouseover(function () {
      $(this).find(".depth2").stop().slideDown();
    })
    .mouseleave(function () {
      $(this).find(".depth2").stop().slideUp();
    });

  // swiper
  var swiper = new Swiper(".swiper-area", {
    pagination: {
      el: ".swiper-pagination",
      dynamicBullets: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
    autoplay: {
      delay: 2000,
      disableOnInteraction: false,
    },
    loop: true,
  });

  //Footer Swiper
  var swiper1 = new Swiper(".FooterSlider", {
    spaceBetween: 30,
    centeredSlides: true,
    autoplay: {
      delay: 1500,
      disableOnInteraction: false,
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
    loop: true,
  });

  // popup
  $(".divPop").css({
    top: ($(window).height() - $(".divPop").height()) / 2,
    left: ($(window).width() - $(".divPop").width()) / 2,
  });

  $(".divPop").draggable();

  $(window).resize(function () {
    $(".divPop").css({
      top: ($(window).height() - $(".divPop").height()) / 2,
      left: ($(window).width() - $(".divPop").width()) / 2,
    });
  });

  // tab
  $(".board_title").click(function () {
    $(".board_title").removeClass("on");
    $(this).addClass("on");
    console.log($(this).index(".board_title"));
    console.log($(this).text());

    // if ($(this).index('.board_title') == 0) {
    //     $('#board_notice').show();
    //     $('#board_data').hide();
    // } else {
    //     $('#board_notice').hide();
    //     $('#board_data').show();
    // }

    // 사용자 정의 속성
    console.log($(this).data("type"));

    $(".board_content").hide();
    $("#" + $(this).data("type")).show();
  });
});
