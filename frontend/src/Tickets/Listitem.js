
import Modal from 'react-bootstrap/Modal'
import React, { Component } from 'react'
import Button from 'react-bootstrap/Button'
export default class Listitem extends Component {

    constructor(props) {
        super(props)

        this.state = {
            popUp: false
        }
        this.popupClose = this.popupClose.bind(this)
    }



    popuphandle = () => {
        this.setState(
            {
                popUp: true
            }
        )
    }

    popupClose = () => {
        this.setState({
            popUp:false
        })

    }

    render() {
        return (
            <div >
            <a  className="list-group-item list-group-item-action"  style={{  backgroundColor: "cadetblue",marginBottom:"15px", cursor:"pointer"}} >
                <div className="row">
                    <div className="col-md-4">
                       <p onClick={this.popuphandle} > {this.props.data.id}</p>
                    </div>
                    <div className="col-md-4">
                       <p onClick={this.popuphandle}> {this.props.data.subject}</p>
                    </div>
                    <div className="col-md-4">
                       <p onClick={this.popuphandle}> {this.props.data.requester_id}</p>
                    </div>
                </div>
                {this.state.popUp === true ?<div> <Modal
                    size="lg"
                    show={this.state.popUp}
                     
                    aria-labelledby="example-modal-sizes-title-lg"
                    style={{backgroundColor:"seashell"}}                >
                    <Modal.Header>
                        <Modal.Title id="example-modal-sizes-title-lg">
                            {this.props.data.subject}  
                        </Modal.Title>
                        <p>creation time: {this.props.data.created_at}</p>
                    </Modal.Header>
                    <Modal.Body>
                        {this.props.data.description}
                    </Modal.Body>
                    <Modal.Footer>
                       <div className="container">
                       <div className="row">
                         <div className = "col-md-3">
                              <p>assignee: {this.props.data.assignee_id}</p>
                        </div>
                        <div className = "col-md-3">
                        <p>requester: {this.props.data.requester_id}</p>
                        </div>
                        <div className = "col-md-3">
                        <p>submitter: {this.props.data.submitter_id}</p>
                        </div>

                        <div  className = "col-md-3">     
                       <Button onClick={this.popupClose}>Close</Button>
                       </div>
                        </div>

                       </div>
                    </Modal.Footer>
                </Modal></div>

                    : ""}
            </a>
            </div>
        )
    }
}
