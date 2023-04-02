import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

function TaskList(props) {
  const [tasks, setTasks] = useState([]);
  const [editTaskId, setEditTaskId] = useState(null);
  const [editedTaskName, setEditedTaskName] = useState("");
  const [editedOwner, setEditedOwner] = useState("");

  const fetchData = () => {
    axios.get("http://localhost:8080/tasks").then((result) => {
      console.log(result);
      setTasks(result.data);
    });
  };

  useEffect(() => {
    if (props.refreshTasks === true) {
      fetchData();
    }
    fetchData();
  }, [props.refreshTasks]);

  const handleEdit = (task) => {
    setEditTaskId(task.taskId);
    setEditedTaskName(task.taskName);
    setEditedOwner(task.owner);
  };

  const handleSave = (taskId) => {
    axios
      .put("http://localhost:8080/tasks/" + taskId, {
        taskName: editedTaskName,
        owner: editedOwner,
      })
      .then((result) => {
        fetchData();
        setEditTaskId(null);
      });
  };

  const handleDelete = (id) => {
    axios.delete("http://localhost:8080/tasks/" + id).then((result) => {
      fetchData();
    });
  };

  return (
    <div className="task-list-container">
      <h1>My To-Do List</h1>
      <table className="task-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Task Name</th>
            <th>Start Time</th>
            <th>Owner</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {tasks.map((task) => (
            <tr key={task.taskId}>
              <td>{task.taskId}</td>
              <td>
                {editTaskId === task.taskId ? (
                  <input
                    type="text"
                    value={editedTaskName}
                    onChange={(e) => setEditedTaskName(e.target.value)}
                  />
                ) : (
                  task.taskName
                )}
              </td>
              <td>{task.startTime}</td>
              <td>
                {editTaskId === task.taskId ? (
                  <input
                    type="text"
                    value={editedOwner}
                    onChange={(e) => setEditedOwner(e.target.value)}
                  />
                ) : (
                  task.owner
                )}
              </td>
              <td>
                {editTaskId === task.taskId ? (
                  <button onClick={() => handleSave(task.taskId)}>Save</button>
                ) : (
                  <button onClick={() => handleEdit(task)}>Edit</button>
                )}
              </td>
              <td>
                <button onClick={() => handleDelete(task.taskId)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TaskList;
