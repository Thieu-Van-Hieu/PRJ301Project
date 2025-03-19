class Message {
    constructor(id, clubId, memberId, memberName, message) {
        this.id = id;
        this.clubId = clubId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.message = message;
    }

    getId() {
        return this.id;
    }

    getClubId() {
        return this.clubId;
    }

    getMemberId() {
        return this.memberId;
    }

    getMemberName() {
        return this.memberName;
    }

    getMessage() {
        return this.message;
    }
}
