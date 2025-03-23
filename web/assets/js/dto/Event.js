class Event {
    constructor(id, name, clubId, clubName, startDate, endDate, imgUrl) {
        this.id = id;
        this.name = name;
        this.clubId = clubId;
        this.clubName = clubName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgUrl = imgUrl;
    }

    getId() {
        return this.id;
    }

    getName() {
        return this.name;
    }

    getClubName() {
        return this.clubName;
    }

    getStartDate() {
        return this.startDate;
    }

    getEndDate() {
        return this.endDate;
    }

    getImgUrl() {
        return this.imgUrl;
    }
}
