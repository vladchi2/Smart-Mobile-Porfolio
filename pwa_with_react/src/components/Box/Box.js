import React from 'react';
import PropTypes from 'prop-types';


const Box = () => (
  <motion.div
    animate={{ scale: 2 }}
    transition={{ duration: 0.5 }}
  />
);

Box.propTypes = {};

Box.defaultProps = {};

export default Box;
