import { createContext, useState, useEffect } from "react";
import { getStudents } from "../services/api";

export const StudentContext = createContext();

export const StudentProvider = ({ children }) => {
  const [students, setStudents] = useState([]);

  const fetchStudents = async () => {
    try {
      const res = await getStudents();
      setStudents(res.data);
    } catch (err) {
      console.error("Error fetching students:", err);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  return (
    <StudentContext.Provider value={{ students, fetchStudents }}>
      {children}
    </StudentContext.Provider>
  );
};