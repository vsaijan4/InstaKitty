<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="profile.name"
          name="name"
        />
      </div>
      <div class="form-group">
        <label for="birthday">Birthday</label>
        <input
          type="date"
          class="form-control"
          id="birthday"
          required
          v-model="profile.birthday"
          name="birthday"
        />
        {{date}}
      </div>
      <div class="form-group">
        <label for="gender">Gender</label>
        <input
          type="text"
          class="form-control"
          id="gender"
          required
          v-model="profile.gender"
          name="gender"
        />
      </div>
      <div class="form-group">
        <label for="type">Type</label>
        <input
          type="text"
          class="form-control"
          id="type"
          required
          v-model="profile.type"
          name="type"
        />
      </div>
      <button @click="saveProfile" class="btn btn-success">Submit</button>
    </div>
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newProfile">Add</button>
    </div>
  </div>
</template>
<script>
import moment from 'moment';
import ProfileDataService from "../services/ProfileDataService";

export default {
  computed: {
  date: function () {
    return moment(this.profile.birthday).format('DD-MM-YYYY')
    }
  },
  name: "add-profile",
  data() {
    return {
      profile: {
        id: null,
        name: "",
        birthday: "",
        gender: "",
        type: ""
      },
      submitted: false
    };
  },
  methods: {
    saveProfile() {
      var data = {
        name: this.profile.name,
        birthday: moment(this.profile.birthday).format('DD-MM-YYYY'),
        gender: this.profile.gender,
        type: this.profile.type
      };
      ProfileDataService.create(data)
        .then(response => {
          this.profile.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newProfile() {
      this.submitted = false;
      this.profile = {};
    }
  }
};
</script>
<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
