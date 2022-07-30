document.addEventListener("DOMContentLoaded", function() {
   let dateInputs = document.querySelectorAll('input[date-input]');
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

//   function checkDayInFeb (dateArr) {
//        if (dateArr[1] = '02') {
//            return checkDateFeb(dateArr);
//        }
//        return dateArr[0];
//   }

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
//
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
});
