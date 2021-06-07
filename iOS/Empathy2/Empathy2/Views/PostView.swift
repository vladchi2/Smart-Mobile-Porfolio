//
//  PostView.swift
//  Empathy2
//
//  Created by Mihail on 12/04/2021.
//
import SwiftUI
import UIKit
import Firebase

struct PostView: View {
    
    let db = Firestore.firestore()
    
    let posts: [Post] = [
        .init(id:0,time: "4 hrs ago", body: "Some Info to be added according to the first line of the last initialisation on the transport thingie", image: "user1",user: "Billy Boy"),
        .init(id:1,time: "3 hrs ago", body: "Some Info to be added according to the first line of the last initialisation on the transport thingie", image: "user1",user: "Nathalia Gornicioy"),
        .init(id:2,time: "18 minutes ago", body: "Some Info to be added according to the first line of the last initialisation on the transport thingie", image: "user1",user: "Savaje Levan"),
        .init(id:3,time: "yesterday", body: "Some Info to be added according to the first line of the last initialisation on the transport thingie", image: "user1",user: "Bruno Pluto")
        ]
    
    
    var body: some View {
        NavigationView{
          
            
            
            List{
                Button(action: {addToDB()}, label: {
                    /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
                })
            // Trending Group
              TrendLine()
                    
                //------Posts---------
                ForEach(posts, id: \.id) { post in
                    PostRow(post: post)
                }
            }.navigationTitle("Feed")
            
        }
    }


    func addToDB(){
        db.collection("posts").addDocument(data: ["body":posts[1].body]) {(error) in
            if let e = error{
                print("There was an error, \(e)")
            }
            else {
                print("Success")
            }
        }
    }




struct PostView_Previews: PreviewProvider {
    static var previews: some View {
        PostView()
            .preferredColorScheme(.dark)
    }
}

}
