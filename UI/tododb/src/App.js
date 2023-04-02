import React from "react";
import TaskTable from "./TaskTable";
import TaskForm from "./TaskForm";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tasks: [],
      refreshFetchTasks: false
    };
  }

  refreshTasks = () => {
    this.setState(prevState => ({
      refreshFetchTasks: !prevState.refreshFetchTasks
    }));
  };
  

  render() {
    return (
      <div>
        <h1>To Do List</h1>
        <TaskForm refreshTasks={this.refreshTasks} />
        <TaskTable tasks={this.state.tasks} refreshTasks={this.refreshTasks} />
        {/* <TaskTable renderTasks={this.renderTasks}/> */}
      </div>
    );
  }
}

export default App;
