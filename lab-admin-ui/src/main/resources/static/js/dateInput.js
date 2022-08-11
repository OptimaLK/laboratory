document.addEventListener("DOMContentLoaded", function() {
   let dateInputs = document.querySelectorAll('input[date-input]');
   let timeInputs = document.querySelectorAll('input[time-input]');
   let dateStart = document.querySelector('#birthTime');
   let dateEnd = document.querySelector('#LifeTime');
   /**
    * проверка високостного года
    * @param {*} year
    * @returns
    */
   function checkLeapYear(year) {
       return ((0 == year % 4) && (0 != year % 100) || (0 == year % 400));
   }

   /**
    * парсит введенную дату
    * @param {*} date
    * @returns array(день,месяц,год)
    */
   function parsDate(date) {
       return date.split('.');
   }

   /**
    * проверяет день месмяца в феврале
    * @param {*} date
    * @returns
    */
   function checkDateFeb (date) {
       let dateArr = parsDate(date);
       if (checkLeapYear(dateArr[2])) {
           return dateArr[0] < 29 ? dateArr[0] : 29;
       }
       return dateArr[0] < 28 ? dateArr[0] : 28;
   }

   /**
   * проверяет месяц в году
   * @param {String} date
   * @returns при введении несуществующего месяца (> 12 || < 01) в ujle
   * возвращает ближайщий возможный (01 || 12 )
   */

   function checkMonthInYear (date) {
       let dateArr = parsDate(date);
       if (parsDate(date)[1] > 12 ) return '12';
       if (parsDate(date)[1] < 1)  return '01';
       return parsDate(date)[1];
   }

   /**
    * проверяет день в месяце
    * @param {String} date
    * @returns при введении несуществующего дня (32..99) в месяце
    * возвращает последний день (30 || 31 || 29)
    */

   function checkDayInMonth (date){
       let dateArr = parsDate(date);
       switch (dateArr[1]) {
           case( '01' || '03' || '05' || '07' || '08' || '10' || '12' ): {
               return dateArr[0] <= 31 ? dateArr[0] : '31';
           };
           case( '04' || '06' || '09' || '11' ): {
               return dateArr[0] <= 30 ? dateArr[0] : '30';
           };
           case( '02' ): {
               if (dateArr[2] === undefined) {
                    return dateArr[0] <=29 ? dateArr[0] : '29';
               } else {
                    return checkDateFeb(date);
               }
           };
           default: return dateArr[0];
       }
   }

   function checkDay (date) {
        let dateArr = parsDate(date);
        if (dateArr[0] > 31) {
            return '31';
        }
        if (dateArr[0] < 1) {
            return '01';
        }
        return dateArr[0];
   }

    /**
    * парсит введенное время
    * @param {String} time
    * @returns array(часы,минуты)
    */
   function parsTime(time) {
       return time.split(':');
   }

   /**
   * проверяет часы в часах :)
   * @param {String} time
   * @returns при введении несуществующего часа (00 || >23) в дне
   * возвращает ближайший существующий (01 || 23)
   */

    function checkHour (time) {
        let timeArr = parsTime(time);
        if (timeArr[0] < 1) {
            return '01';
        }
        if (timeArr[0] > 23) {
            return '23';
        }
        return timeArr[0];
   }

   /**
  * проверяет часы в часах :)
  * @param {String} time
  * @returns при введении несуществующих минут (00 || >59) в часе
  * возвращает ближайший существующий (01 || 59)
  */

   function checkMinute (time) {
       let timeArr = parsTime(time);
       if (timeArr[1] < 1) {
           return '01';
       }
       if (timeArr[1] > 59) {
           return '59';
       }
       return timeArr[1];
  }


   let getInputNumbersValue = function(input){
      return input.value.replace(/\D/g, "")
   }

   let onDateInput = function (e) {
      let input = e.target,
          inputNumbersValue = getInputNumbersValue(input),
         formattedInputValue = "",
         selectionStart = input.selectionStart; // положение курсора

      if (!inputNumbersValue){
         return input.value = "";
      }

      if (inputNumbersValue.length > 0) {
         formattedInputValue += inputNumbersValue.substring(0, 2);
      }
      if (inputNumbersValue.length > 2) {
         formattedInputValue = checkDay(formattedInputValue);
         formattedInputValue += "." + inputNumbersValue.substring(2, 4);
      }

      if (inputNumbersValue.length > 4) {
         formattedInputValue = formattedInputValue.substring(0, 3) + checkMonthInYear (formattedInputValue);
         formattedInputValue = checkDayInMonth(formattedInputValue) + formattedInputValue.substring(2);
         formattedInputValue += "." + inputNumbersValue.substring(4, 8)
      }
      if (inputNumbersValue.length == 8) {
         formattedInputValue = checkDayInMonth(formattedInputValue) + formattedInputValue.substring(2);
      }

      if (input.value.length !== selectionStart) { // Середина строки
         if (e.data && /\D/g.test(e.data)) { // Когдамы что - то вводим и это не цифры
            input.value = inputNumbersValue;
         }
         return;
      }
      if (input.value.length !== selectionStart) { // Середина строки
         if (inputNumbersValue.length > 8) {
            formattedInputValue += inputNumbersValue.substring(0, 8);
         }
      }

      input.value = formattedInputValue;

   }

   let onDateKeyDown = function(e) {
      let input = e.target;
      if (e.keyCode === 8 && getInputNumbersValue(input).length === 1){
         input.value = "";
      }
   }
   for (i = 0; i < dateInputs.length; i++){
      let input = dateInputs[i];
      input.addEventListener("input", onDateInput);
      input.addEventListener("keydown", onDateKeyDown)
   }

   let onTimeInput = function (e) {
      let input = e.target,
          inputNumbersValue = getInputNumbersValue(input),
          formattedInputValue = "",
          selectionStart = input.selectionStart; // положение курсора

      if (!inputNumbersValue){
         return input.value = "";
      }

      if (inputNumbersValue.length > 0) {
         formattedInputValue += inputNumbersValue.substring(0, 2);
      }
      if (inputNumbersValue.length > 2) {
         formattedInputValue = checkHour(formattedInputValue);
         formattedInputValue += ":" + inputNumbersValue.substring(2, 4);
      }
      if (inputNumbersValue.length === 4) {
         formattedInputValue = checkHour(formattedInputValue) + ':' + checkMinute(formattedInputValue);
      }

      if (input.value.length !== selectionStart) { // Середина строки
         if (e.data && /\D/g.test(e.data)) { // Когдамы что - то вводим и это не цифры
            input.value = inputNumbersValue;
         }
         return;
      }
      input.value = formattedInputValue;

   }

   for (i = 0; i < timeInputs.length; i++){
      let input = timeInputs[i];
      input.addEventListener("input", onTimeInput);
      input.addEventListener("keydown", onDateKeyDown)
   }
});
