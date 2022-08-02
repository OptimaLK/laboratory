document.addEventListener("DOMContentLoaded", function() {
   let dateInputs = document.querySelectorAll('input[date-input]');
   let timeInputs = document.querySelectorAll('input[time-input]');
   let dateStart = document.querySelector('#birthTime');
   let dateEnd = document.querySelector('#LifeTime');

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
         if (formattedInputValue > 31) {
            formattedInputValue = 31;
         }
         formattedInputValue += "." + inputNumbersValue.substring(2, 4);
      }
      if (inputNumbersValue.length > 4) {
         if (formattedInputValue.substring(3, 5) > 12) {
            formattedInputValue = inputNumbersValue.substring(0, 2) + "." + 12;
         }
         formattedInputValue += "." + inputNumbersValue.substring(4, 8);
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
         if (formattedInputValue > 23) {
            formattedInputValue = 23;
         }
         formattedInputValue += ":" + inputNumbersValue.substring(2, 4);
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
