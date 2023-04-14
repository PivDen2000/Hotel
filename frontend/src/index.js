
function submitBooking(event) {

  // Prevent the form from submitting
  event.preventDefault();

  // Get the form data
  const form = document.getElementById("booking-form");
  const formData = new FormData(form);

  formData.append("name", form.querySelector('input[name="name"]').value);
  formData.append("numberOfGuests", form.querySelector('input[name="numberOfGuests"]').value);
  formData.append("checkInDate", form.querySelector('input[name="checkInDate"]').value);
  formData.append("checkOutDate", form.querySelector('input[name="checkOutDate"]').value);
  formData.append("roomType", form.querySelector('select[name="roomType"]').value);

  // Convert the form data to JSON format
  const jsonObject = {};
  formData.forEach((value, key) => {
    jsonObject[key] = value;
  });

  // Create the request
  fetch("http://localhost:5000/booking/", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(jsonObject),
  });

  // Reset the form
  form.reset();
}
