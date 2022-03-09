<template>
  <div v-if="currentProfile" class="edit-form">
    <h4>Profile</h4>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name"
          v-model="currentProfile.name"
        />
      </div>
      <div class="form-group">
        <label for="birthday">Birthday</label>
        <input type="date" class="form-control" id="birthday"
          v-model="currentProfile.birthday"
        />
      </div>
      <div class="form-group">
        <label for="gender">Gender</label>
        <input type="text" class="form-control" id="gender"
          v-model="currentProfile.gender"
        />
      </div>
      <div class="form-group">
        <label for="type">Type</label>
        <input type="text" class="form-control" id="type"
          v-model="currentProfile.type"
        />
      </div>
    </form>

    <button class="badge badge-danger mr-2"
      @click="deleteProfile"
    >
      Delete
    </button>
    <button type="submit" class="badge badge-success"
      @click="updateProfile"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>
  <div v-else>
    <br />
    <p>Please click on a Profile...</p>
  </div>
</template>
<script>
import ProfileDataService from "../services/ProfileDataService";
export default {
  name: "profile",
  data() {
    return {
      currentProfile: null,
      message: ''
    };
  },
  methods: {
    getProfile(id) {
      ProfileDataService.get(id)
        .then(response => {
          this.currentProfile = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateProfile() {
      ProfileDataService.update(this.currentProfile.id, this.currentProfile)
        .then(response => {
          console.log(response.data);
          this.message = 'The profile was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteProfile() {
      ProfileDataService.delete(this.currentProfile.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "profiles" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getProfile(this.$route.params.id);
  }
};
</script>
<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>