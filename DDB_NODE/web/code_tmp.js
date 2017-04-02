

    script.
      /* pour faire fonctionner la barre de nav */
      $('.menu-item').click(function () {
      $('.selected').children().removeClass('selected-link');
      $('.selected').removeClass('selected');
      $(this).addClass('selected');
      $(this).children().addClass('selected-link');
      });
      /* navbar script end */
