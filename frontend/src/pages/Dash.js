import StudentTable from "../components/StudentTable";
import AddStudentForm from "../components/AddStudentForm";
import { Container, Typography } from "@mui/material";

const Dashboard = () => {
  return (
    <Container>
      <Typography variant="h4" gutterBottom>
        Student Dashboard
      </Typography>

      <AddStudentForm />
      <StudentTable />
    </Container>
  );
};

export default Dashboard;