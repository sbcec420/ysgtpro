document.addEventListener("DOMContentLoaded", () => {
  function checkConnection() {
    if (!navigator.onLine) {
      window.location.href = "nointernet.html";
    }
  }

  // যখন নেট বন্ধ হবে
  window.addEventListener("offline", checkConnection);

  // যখন অ্যাপ প্রথম খুলবে তখনও চেক করো
  checkConnection();
});