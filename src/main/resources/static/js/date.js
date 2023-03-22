
const elements = document.querySelectorAll(".date");

elements.forEach((element) => {
  const str = element.textContent.trim();
   const [dateStr, timeStr] = str.split(' ');
   const [year, month, day] = dateStr.split("-");
   const [hour, minute, second] = timeStr.split(":");

   const message = `${day}.${month - 1}.${year} ${hour}:${minute}`;
   element.textContent = message;
 });

