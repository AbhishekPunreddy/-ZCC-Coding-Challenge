import React, { Component } from 'react'
import axios from 'axios'
import { server } from '../Constants/server'
import Listitem from './Listitem'


export default class Tickets extends Component {

    constructor(props) {
        super(props)

        this.state = {

            ticketsarray:[],
            loading: true,
            nextPointer: "",
            prevPointer: "",
            error: false

        }
    }

    componentDidMount() {

        axios.get(`${server.url}getAllTickets`)
            .then(res => {

                if(res.status ==200)
                {
                this.setState({
                    ticketsarray: res.data.tickets,
                    nextPointer: res.data.meta.after_cursor,
                    prevPointer: res.data.meta.before_cursor,
                    loading:false
                })
            }else
            {
                alert(res.error)
            }
               


            })
            .catch(err => {
                alert("Server Error");
                this.setState(
                    {
                        loading: false,
                        error: true,
                        
                    }
                )

            }
            )
    }

    fetchNextpage = () => {
        axios.get(`${server.url}getNextOrPreviousTickets/after/${this.state.nextPointer}`)
            .then(res => {

                if (res.data.meta.after_cursor != null) {
                    this.setState(
                        {
                            ticketsarray: res.data.tickets,
                            nextPointer: res.data.meta.after_cursor,
                            prevPointer : res.data.meta.before_cursor
                        }
                    )
                } else {
                    alert("You have reached the last page")
                }



            })

    }


    fetchPrevPage = () => {

        axios.get(`${server.url}getNextOrPreviousTickets/before/${this.state.prevPointer}`)
            .then(res => {
                if (res.data.meta.before_cursor != null) {
                     
                    this.setState(
                        {
                            ticketsarray: res.data.tickets,
                            nextPointer: res.data.meta.after_cursor,
                            prevPointer: res.data.meta.before_cursor
                        }
                    )
                }
                else {
                    alert("You are in the first page")
                }
            }
            )
    }
    render() {
        return (
            <div className="container-fluid">   
                    {this.state.loading==true?<div>
                    <div class="spinner-border text-primary" role="status" style={{marginTop:"20%"}}>
                       <span class="sr-only"></span>
                    </div>
                    <p>Loading</p>
                </div>:""
                }
                <div className="row">
                    {this.state.loading!=true?<h5 style={{fontSize:"30px", marginTop: "50px"}}>Zendesk Ticket Viewer</h5>:""
                    }
                <center>
                    <div className="col-md-8" style={{marginTop:"50px"}}>
                      {/* {this.state.loading !=true? */}
                      <div>
                          <div className="list-group" style={{backgroundColor:"snow"}}>
                              {this.state.ticketsarray.map((data,key)=>{
                               return <Listitem data = {data}></Listitem>
                              }
                              )}
                        </div>
                      </div>:
                      {/* ""}    */}
                    </div>
                    {this.state.loading != true?<div className="col-md-2">
                    <button type="button" style={{marginRight:"10px"}} onClick={this.fetchPrevPage} class="btn btn-info">Prev</button>
                    <button type="button" onClick={this.fetchNextpage} class="btn btn-info">Next</button>
                    </div>:""
                         }
                    </center>
                </div>

            </div>
        )
    }
}
