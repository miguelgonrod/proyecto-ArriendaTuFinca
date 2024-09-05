  document.addEventListener("DOMContentLoaded", function() {
    document.querySelector('.contact-form').addEventListener('submit', function(event) {
      var emailInput = document.getElementById('email');
      var email = emailInput.value;
      var emailPattern = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

      if (email.length > 100 || !emailPattern.test(email)) {
        alert("El correo debe contener '@' seguido de un punto, no debe tener más de 100 caracteres, no debe contener espacios ni caracteres especiales, y debe estar en mayúsculas.");
        event.preventDefault();
      }
    });
  });