
const arr = document.querySelectorAll('.comment');
const elementArray = [...arr];
const data = elementArray.map((el) => {
  const str = el.textContent.trim();
  const [dateStr, timeStr] = str.split(" ");
  const [year, month, day] = dateStr.split("-");

  const [hour, minute, second] = timeStr.split(":");
  const date = new Date(year, month - 1, day, hour, minute, second);
  const message1 = str.split(" ").slice(2).join(" ");
  const message = `${hour}:${minute}:${second} ${day}.${month - 1}.${year} ${message1}`;
  return { date, message };
});

data.sort((a, b) => {
  if (a.date.getTime() === b.date.getTime()) { // Если даты равны, сравниваем время
    return a.date.getMilliseconds() - b.date.getMilliseconds();
  } else {
    return b.date - a.date;
  }
});

const parentElement = document.querySelector('.parent');
parentElement.innerHTML = '';
//console.log(data.message);

data.forEach((item) => {
  const messageElement = document.createElement("div");
  messageElement.textContent = item.message;
  parentElement.appendChild(messageElement);
});