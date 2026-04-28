import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080"
});

export const getStudents = () => API.get("/students");

export const addStudent = async (data) => {
  try {
    const res = await API.post("/students/single", data);
    return res;
  } catch (err) {
    return err.response;
  }
};