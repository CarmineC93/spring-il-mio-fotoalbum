// GLOBAL VARIABLES
const PHOTOS_URL = 'http://localhost:8080/api/photos';

const MESSAGE_URL = 'http://localhost:8080/api/mailbox';

const contentElement = document.getElementById('photos-gallery');
const toggleForm = document.getElementById('toggle-form');
const messageForm = document.getElementById('message-form');
const searchForm = document.getElementById('search-form');
const keyword = document.getElementById('keyword');

// API REQUESTS
const getAllPhotos = async () => {
    const param = keyword.value;
    console.log(keyword);
    const response = await fetch(PHOTOS_URL + '?keyword=' + param); // fetch default = GET
    return response;
};

  //send post request with message in body 
const postMessage = async (jsonData) => {
    const fetchOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: jsonData,
    };
    const response = await fetch(MESSAGE_URL, fetchOptions);
    return response;
  };
  

//DOM MANIPULATION

const toggleFormVisibility = () => {
    document.getElementById('form').classList.toggle('d-none');
  };

  //create element photo
const createPhotoItem = (item) => {

    let category = "";
    item.categories.forEach(function(categ){
      category +=" #" + categ.name;
    });
    
      return `<div class="col-4" id="photo-card">
                <div class="card h-100">
                    <div class="card-body p-0">
                        <img style="max-width: 100%;" src="${item.url}" alt="${item.description}">
                    </div>
                    <div class="card-footer">
                    <h5>${item.title}</h5>
                    <h6>${item.description}</h6>
                    <h6 class="text-info"> ${category} </h6>
                    </div>
                </div>
        </div>`;
    
  };

  // function that print element photo in page from data
const createPhotoList = (data) => {
    console.log(data);
    let list = `<div class="row gy-3">`;
    // add photo items
    data.forEach((element) => {
      //check visibility status done in backend
      //if(element.visible){
        list += createPhotoItem(element);
      //}
    });
    list += '</div>';
    return list;
  };

//PRINCIPAL FUNCTIONS 

const loadData = async() =>{
    //call api request
    const response = await getAllPhotos();
    console.log(response);
    if(response.ok){
        //get data
        const data = await response.json();
        console.log(data);
        //render html
        contentElement.innerHTML = createPhotoList(data);
  };
}

  //save new Message from site visitor
const saveMessage = async (event) => {
    // prevent default
    event.preventDefault();
    // read input values
    const formData = new FormData(event.target);
    const formDataObj = Object.fromEntries(formData.entries());
    // produce a json
    const formDataJson = JSON.stringify(formDataObj);
    // send ajax request
    const response = await postMessage(formDataJson);
    

    if (response.ok) {
      // reload data
      loadData();
      // reset form and clean fields
      event.target.reset();
    } else {
      // handle error
      console.log('ERROR', response.status, responseBody);
    }

    // parse response //what does it do??
    // const responseBody = await response.json(); 
  };


//GLOBAL CODE
toggleForm.addEventListener('click', toggleFormVisibility);
messageForm.addEventListener('submit', saveMessage);
searchForm.addEventListener('submit', loadData);

loadData();

