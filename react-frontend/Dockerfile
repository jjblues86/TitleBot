# Use an official Node runtime as a parent image
FROM node:16-alpine

# Set the working directory to /app
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the app files to the working directory
COPY . .

# Build the app for production
RUN npm run build

# Expose port 3000 for the React app
EXPOSE 3000

# Start the app
CMD ["npm", "start"]
