import { useContext, useState } from "react";
import { StudentContext } from "../context/StudentContext";
import {
  Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, TextField
} from "@mui/material";

const StudentTable = () => {
  const { students } = useContext(StudentContext);
  const [search, setSearch] = useState("");

  const filteredStudents = students.filter((s) =>
    (
      s.firstName +
      s.lastName +
      s.email +
      s.courseName +
      s.phoneNumber
    )
      ?.toLowerCase()
      .includes(search.toLowerCase())
  );

  return (
    <>
      <TextField
        label="Search Students"
        fullWidth
        margin="normal"
        onChange={(e) => setSearch(e.target.value)}
        inputProps={{ "data-testid": "search-input" }}
      />

      <TableContainer component={Paper}>
        <Table data-testid="student-table">
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>First Name</TableCell>
              <TableCell>Last Name</TableCell>
              <TableCell>DOB</TableCell>
              <TableCell>Gender</TableCell>
              <TableCell>Phone</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Course</TableCell>
              <TableCell>Blood Group</TableCell>
              <TableCell>CGPA</TableCell>
            </TableRow>
          </TableHead>

          <TableBody>
            {filteredStudents.map((s, index) => (
              <TableRow key={index}>
                <TableCell>{s.id}</TableCell>
                <TableCell>{s.firstName}</TableCell>
                <TableCell>{s.lastName}</TableCell>
                <TableCell>
                  {s.dateOfBirth ? new Date(s.dateOfBirth).toLocaleDateString() : ""}
                </TableCell>
                <TableCell>{s.gender}</TableCell>
                <TableCell>{s.phoneNumber}</TableCell>
                <TableCell>{s.email}</TableCell>
                <TableCell>{s.courseName}</TableCell>
                <TableCell>{s.bloodGroup}</TableCell>
                <TableCell>{s.cgpa}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
};

export default StudentTable;