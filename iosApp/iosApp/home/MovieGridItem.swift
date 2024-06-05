//
//  MovieGridItem.swift
//  iosApp
//
//  Created by Manuel Gonzalez on 05/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieGridItem: View {
    let movie: Movie
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            ZStack {
                AsyncImage(url: URL(string: movie.imageUrl)) {image in
                    image.resizable()
                } placeholder: {
                    Color.gray
                }
                Circle()
                    .frame(width: 50, height: 50)
                    .foregroundColor(.black.opacity(0.6))
                Image(systemName: "play.fill")
            }
            .frame(maxWidth: .infinity, idealHeight: .infinity)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            
            Text(movie.title)
                .font(.title3)
                .fontWeight(.bold)
                .lineLimit(1)
            
            Text(movie.releaseDate)
                .font(.caption)
        }
        .frame(maxWidth: .infinity, maxHeight: 260)
    }
}

#Preview {
    MovieGridItem(movie: sampleMovie)
}