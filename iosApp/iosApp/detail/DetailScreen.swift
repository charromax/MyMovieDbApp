//
//  DetailScreen.swift
//  iosApp
//
//  Created by Manuel Gonzalez on 05/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
struct DetailScreen: View {
    let movie: Movie
    
    var body: some View {
        ScrollView {
            VStack {
                ZStack{
                    AsyncImage(url: URL(string:movie.imageUrl)){image in
                        image.resizable().scaledToFill()
                    } placeholder: {
                        ProgressView()
                    }
                }
                .frame(maxWidth: .infinity, maxHeight: 300)
                
                VStack(alignment: .leading, spacing: 12) {
                    Text(movie.title)
                        .font(.title)
                        .fontWeight(.bold)
                    
                    Button(action: {}) {
                        HStack {
                            Image(systemName: "play.fill").foregroundColor(.black)
                            Text("Start watching now!").fontWeight(.bold).foregroundColor(.white)
                        }
                    }
                    .frame(maxWidth: .infinity, maxHeight: 40)
                        .padding()
                        .background(.red)
                        .clipShape(RoundedRectangle(cornerRadius: 8.0))
                    
                    Text("released in \(movie.releaseDate)".uppercased())
                    
                    Text(movie.description_)
                        .font(.body)
                        .fontWeight(.bold)
                        .fixedSize(horizontal: false, vertical: true)
                }
                .padding(20)
                .background(.black)
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
    }
}

struct DetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        DetailScreen(movie: sampleMovie)
    }
}

