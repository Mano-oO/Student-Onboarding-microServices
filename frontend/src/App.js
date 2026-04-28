import Dashboard from "./pages/Dash";
import { StudentProvider } from "./context/StudentContext";

function App() {
  return (
    <StudentProvider>
      <Dashboard />
    </StudentProvider>
  );
}

export default App;