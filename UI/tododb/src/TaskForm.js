import React, { useState } from "react";
import axios from "axios";
import "./TaskForm.css";

const TaskForm = ({ refreshTasks }) => {
  const [taskName, setTaskName] = useState("");
  const [startTime, setStartTime] = useState("");
  const [owner, setOwner] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const task = { taskName, startTime, owner };
    axios
      .post("http://localhost:8080/tasks", task)
      .then(() => {
        setTaskName("");
        setStartTime("");
        setOwner("");
        refreshTasks();
      })
      .catch((err) => console.error(err));
  };

  return (
    <form onSubmit={handleSubmit} className="task-form">
      <div className="form-group">
        <label htmlFor="taskName">Task Name:</label>
        <input
          type="text"
          id="taskName"
          value={taskName}
          onChange={(e) => setTaskName(e.target.value)}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="startTime">Start Time:</label>
        <input
          type="datetime-local"
          id="startTime"
          value={startTime}
          onChange={(e) => setStartTime(e.target.value)}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="owner">Owner:</label>
        <input
          type="text"
          id="owner"
          value={owner}
          onChange={(e) => setOwner(e.target.value)}
          required
        />
      </div>
      <button>Add Task</button>
    </form>
  );
};

export default TaskForm;
