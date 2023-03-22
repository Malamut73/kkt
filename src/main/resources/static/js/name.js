const elementsPP = document.querySelectorAll('.product_parent');

const object = Array.from(elementsPP, (element) => {
  const link = element.querySelector('.name a').href;
  const name = element.querySelector('.name').textContent;
  const ofdDayEnd = new Date(Date.parse(element.querySelector('.ofd_day_end').textContent));
  const fnDayEnd = new Date(Date.parse(element.querySelector('.fn_day_end').textContent));
  const maintenanceDayEnd = new Date(Date.parse(element.querySelector('.maintenance_day_end').textContent));

  return { ofdDayEnd, fnDayEnd, maintenanceDayEnd, link, name };
});

const sortedByOfd = object.slice().sort((a, b) => a.ofdDayEnd - b.ofdDayEnd);
const sortedByFn = object.slice().sort((a, b) => a.fnDayEnd - b.fnDayEnd);
const sortedByMaintenance = object.slice().sort((a, b) => a.maintenanceDayEnd - b.maintenanceDayEnd);

function createElementWithLink(obj, parentElement) {
  const linkElement = document.createElement('a');
  linkElement.href = obj.link;
  linkElement.textContent = obj.name;
  parentElement.appendChild(linkElement);
}

function addDateEnd(obj, parentElementClass) {
  const parentElement = document.querySelector(`.${parentElementClass}`);
  parentElement.innerHTML = '';

  createElementWithLink(obj, parentElement);

  const messageElement = document.createElement('span');
  const dayEndString = `${obj[parentElementClass].getFullYear()}-${(obj[parentElementClass].getMonth()+1).toString().padStart(2, '0')}-${obj[parentElementClass].getDate().toString().padStart(2, '0')}`;

  messageElement.textContent = dayEndString;
  parentElement.appendChild(messageElement);
}

addDateEnd(sortedByOfd[0], 'ofdDayEnd');
addDateEnd(sortedByFn[0], 'fnDayEnd');
addDateEnd(sortedByMaintenance[0], 'maintenanceDayEnd');


