import { useState, useContext } from "react";
import { TextField, Button, Grid, Paper, MenuItem } from "@mui/material";
import { StudentContext } from "../context/StudentContext";

const AddStudentForm = () => {
  const { fetchStudents } = useContext(StudentContext);
  const [form, setForm] = useState({
    first_name: "", last_name: "", date_of_birth: "",
    gender: "", phone_number: "", email: "",
    course_name: "", blood_group: "", cgpa: ""
  });

  const [errors, setErrors] = useState({});
  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

 
  const handleSubmit = async () => {
    try {
      const res = await fetch("http://localhost:8080/students/single", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form)
      });

      const data = await res.json();

      console.log("STATUS:", res.status);
      console.log("DATA:", data);

      if (data && Object.keys(data).length > 0) {
        setErrors({ ...data });   
      } else {
        setErrors({});
        fetchStudents();          
      }

    } catch (e) {
      console.log("FETCH ERROR:", e);
    }
  };

  const fields = [
    { name: "first_name", label: "First Name" },
    { name: "last_name", label: "Last Name" },
    { name: "date_of_birth", type: "date" },
    { name: "phone_number", label: "Phone" },
    { name: "email", label: "Email" },
    { name: "cgpa", label: "CGPA" }
  ];

  const dropdowns = [
    { name: "gender", label: "Gender", options: ["Male", "Female", "Other"] },
    { name: "course_name", label: "Course", options: ["AI", "Cloud", "IT", "Marketing", "QA", "HR"] },
    { name: "blood_group", label: "Blood Group", options: ["A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"] }
  ];

  return (
    <Paper sx={{ p: 2, mb: 2 }}>
      <Grid container spacing={2}>
        {/* <Grid item xs={12}>
          <p>{JSON.stringify(errors)}</p>
        </Grid> */}
        {fields.map(f => (
          <Grid item xs={6} key={f.name}>
            <TextField
              fullWidth
              type={f.type || "text"}
              label={f.label}
              name={f.name}
              onChange={handleChange}
              error={!!errors[f.name]}
              helperText={errors[f.name]}
            />
          </Grid>
        ))}
        {dropdowns.map(d => (
          <Grid item xs={6} key={d.name}>
            <TextField
              select
              fullWidth
              label={d.label}
              name={d.name}
              onChange={handleChange}
              error={!!errors[d.name]}
              helperText={errors[d.name]}
            >
              {d.options.map(opt => (
                <MenuItem key={opt} value={opt}>{opt}</MenuItem>
              ))}
            </TextField>
          </Grid>
        ))}
        <Grid item xs={12}>
          <Button variant="contained" onClick={handleSubmit} id="submit-btn">
            Add Student
          </Button>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default AddStudentForm;