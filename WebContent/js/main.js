
(function($) {
  $(function() {

    webshims.setOptions('enhanceAuto', !(matchMedia('(max-device-width: 720px)').matches || matchMedia('(max-device-width: 1024px)').matches && Modernizr.touchevents));
    webshims.setOptions({
      extendNative: true
    });
    webshims.setOptions('forms', {
      // lazyCustomMessages: true,
      addValidators: true,
      iVal: {
        recheckDelay: 300,
        submitCheck: true,
        errorBoxClass: 'ws-errorbox col-sm-offset-3 col-sm-9',
        errorMessageClass: 'help-block',
        // successWrapperClass: 'has-success',
        // errorWrapperClass: 'has-error',
        errorWrapperClass: 'ws-invalid',
        successWrapperClass: 'ws-success',
        fx: 'slide',
        fieldWrapper: '.form-group'
      }
    });
    webshim.setOptions('forms-ext', {
        replaceUI: 'auto',
        types: 'date',
        date: {
            startView: 2,
            size: 2,
            classes: 'hide-spinbtns'
        }
    });

    //start polyfilling
    webshims.polyfill('forms forms-ext details');

    $('[type="date"].min-today').prop('min', function(){
        return new Date().toJSON().split('T')[0];
    });

    //initial max/min attributes should be done serverside.
    $('#date-from, #date-to').prop('min', function(){
        return new Date().toJSON().split('T')[0];
    });

  });
})(jQuery);
