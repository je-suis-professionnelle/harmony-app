const searchComponent = {
    template: /*html*/
    `
    <div class="field has-addons">
        <div>
            <h2 class="title is-2">{{ titre }}</h2>
        </div>
        <div class="control">
            <input class="input" type="text" placeholder="Find a repository" />
        </div>
        <div class="control">
            <a class="button is-info">
                Search
            </a>

        </div>
        <a class="button">
            <span class="material-symbols-outlined">
                tune
            </span>
        </a>
        <a class="button">
            <span class="material-symbols-outlined">
                sort
            </span>
        </a>
    </div>
    `,
    data() {
        return {}
    },
    methods: {}

};

export default searchComponent;

/* <nav class="search-bar navbar navbar-light" style="background-color: #e3f2fd;">
      <!-- <div class="space-around"> -->
      <form class="form-inline">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
      <div class="filter-options">Filtrer</div>
      <div class="sort-options">Trier</div>
      <!-- </div> -->

    </nav> */