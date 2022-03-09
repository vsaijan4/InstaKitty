import http from "../http-common";

class ProfileDataService {
  getAll() {
    return http.get("/profiles");
  }
  get(id) {
    return http.get(`/profiles/${id}`);
  }
  create(data) {
    return http.post("/profiles", data);
  }
  update(id, data) {
    return http.put(`/profiles/${id}`, data);
  }
  delete(id) {
    return http.delete(`/profiles/${id}`);
  }
  findByName(name) {
    return http.get(`/profiles?name=${name}`);
  }
}

export default new ProfileDataService();
